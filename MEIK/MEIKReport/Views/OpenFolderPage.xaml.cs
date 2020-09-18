using MEIK.Common;
using MEIKReport.Common;
using MEIKReport.Model;
using MEIKReport.Views;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Xml;

namespace MEIKReport
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class OpenFolderPage : Window
    {
        private ScanerHook listener = new ScanerHook();  
        public string SelectedPath { get; set; }
        public OpenFolderPage()
        {
            InitializeComponent();
            listener.ScanerEvent += Listener_ScanerEvent;
            listener.Start();
        }

        protected override void OnClosed(EventArgs e)
        {
            base.OnClosed(e);
            listener.Stop();
        }      

        private void btnOpen_Click(object sender, RoutedEventArgs e)
        {
            if (this.treeView.SelectedItem != null)
            {
                var selectedItem = this.treeView.SelectedItem as TreeViewItem;
                SelectedPath = (string)selectedItem.Tag;
                var userWin = this.Owner as UserList;
                userWin.loadArchiveFolder(SelectedPath);
                this.Close();
            }
            else
            {
                MessageBox.Show(this, App.Current.FindResource("Message_8").ToString());  
            }
        }

        private void btnCancel_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void treeView_Loaded(object sender, RoutedEventArgs e)
        {
            
            //TreeViewItem MeikData = new TreeViewItem { Header = "MEIK Data", IsExpanded = true };
            DirectoryInfo meikDataFolder = new DirectoryInfo(App.reportSettingModel.DataBaseFolder);            
            //添加目录
            var rootItem=AddDirectoryItems(meikDataFolder);
            rootItem.IsExpanded = true;
            this.treeView.Items.Add(rootItem);
            
        }

        private TreeViewItem AddDirectoryItems(DirectoryInfo folder)
        {
            TreeViewItem folderItem = new TreeViewItem { Header = folder.Name,Tag=folder.FullName, IsExpanded = false };
            var set = folder.GetDirectories().OrderByDescending(x => x.Name);
            foreach (var subfolder in set)
            {
                folderItem.Items.Add(AddDirectoryItems(subfolder));
            }
            folderItem.IsSelected = folder.FullName.Equals(SelectedPath);
            if (folderItem.IsSelected) folderItem.IsExpanded = true;
            return folderItem;
        }

        private void btnDel_Click(object sender, RoutedEventArgs e)
        {
            if (this.treeView.SelectedItem != null)
            {
                var selectedItem = this.treeView.SelectedItem as TreeViewItem;
                var SelectedFolder = (string)selectedItem.Tag;
                var parentItme = selectedItem.Parent as TreeViewItem;
                if (parentItme != null)
                {
                    DirectoryInfo selectedDir = new DirectoryInfo(SelectedFolder);
                    MessageBoxResult result = MessageBox.Show(this, App.Current.FindResource("Message_50").ToString(), "Delete Selected Folder", MessageBoxButton.YesNo, MessageBoxImage.Information);
                    if (result == MessageBoxResult.Yes)
                    {
                        DeleteFolder(selectedDir);
                        selectedItem.Visibility = Visibility.Collapsed;
                        parentItme.IsSelected = true;
                    }
                }
            }
            else
            {
                MessageBox.Show(this, App.Current.FindResource("Message_8").ToString());
            }
        }

        /// <summary>
        /// 删除文件夹下所有内容
        /// </summary>
        /// <param name="folder"></param>
        private void DeleteFolder(DirectoryInfo folder)
        {
            FileInfo[] fileInfo = folder.GetFiles();
            //遍历文件
            foreach (FileInfo NextFile in fileInfo)
            {
                NextFile.Delete();
            }

            DirectoryInfo[] folders=folder.GetDirectories();
            foreach (var item in folders)
            {
                DeleteFolder(item);
            }
            folder.Delete();
        }
        

        /// <summary>
        /// 点击搜索按钮处理事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            //添加根目录
            DirectoryInfo meikDataFolder = new DirectoryInfo(App.reportSettingModel.DataBaseFolder);
            //添加目录
            try
            {
                //如果有搜索条件，则根据条件搜索
                if (!String.IsNullOrWhiteSpace(this.txtSurName.Text) || !String.IsNullOrWhiteSpace(this.txtGivenName.Text) ||                    
                    !String.IsNullOrWhiteSpace(this.txtIDNumber.Text) || !String.IsNullOrWhiteSpace(this.txtMobile.Text)||
                    !String.IsNullOrWhiteSpace(this.txtCode.Text))
                {
                    var rootItem = SearchDirectoryItems(meikDataFolder);
                    this.treeView.Items.Clear();
                    this.treeView.Items.Add(rootItem);
                }
                else
                {
                    var rootItem = AddDirectoryItems(meikDataFolder);
                    rootItem.IsExpanded = true;
                    this.treeView.Items.Clear();
                    this.treeView.Items.Add(rootItem);
                }                                
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);  
            }
        }

        /// <summary>
        /// 根据查询条件搜索指定用户档案
        /// </summary>
        /// <param name="folder"></param>
        /// <returns></returns>
        private TreeViewItem SearchDirectoryItems(DirectoryInfo folder)
        {
            TreeViewItem folderItem = new TreeViewItem { Header = folder.Name, Tag = folder.FullName, IsExpanded = true };
            var set = folder.GetDirectories().OrderByDescending(x => x.Name);
            //如果当前目录还有子目录，表示不是用户档案目录，则继续向下搜索
            if (set.Any())
            {
                foreach (var subfolder in set)
                {
                    var searchItem = SearchDirectoryItems(subfolder);
                    if (searchItem != null)
                    {
                        folderItem.Items.Add(searchItem);
                    }
                }
                if (folderItem.Items.Count == 0)
                {
                    return null;
                }
            }
            else//否则查询用户档案是否符合搜索条件
            {
                var strArr=folder.Name.Split(',');
                //如果是有效的用户档案目录
                if (strArr.Length == 2)
                {
                    if (!String.IsNullOrWhiteSpace(this.txtSurName.Text))
                    {
                        if (!strArr[0].EndsWith("-" + this.txtSurName.Text))
                        {
                            return null;
                        }
                    }
                    if (!String.IsNullOrWhiteSpace(this.txtGivenName.Text))
                    {
                        if (!strArr[1].StartsWith(this.txtGivenName.Text))
                        {
                            return null;
                        }
                    }
                    if (!String.IsNullOrWhiteSpace(this.txtCode.Text))
                    {
                        if (!strArr[0].StartsWith(this.txtCode.Text))
                        {
                            return null;
                        }
                    }
                    if (!String.IsNullOrWhiteSpace(this.txtIDNumber.Text) || !String.IsNullOrWhiteSpace(this.txtMobile.Text))
                    {
                        //在用户档案目录，查找.ini文件，
                        FileInfo fileInfo = folder.GetFiles("*.ini", SearchOption.TopDirectoryOnly).FirstOrDefault();
                        if (fileInfo != null)
                        {
                            if (!String.IsNullOrWhiteSpace(this.txtIDNumber.Text))
                            {
                                var clientID = OperateIniFile.ReadIniData("Personal data", "clientID", "", fileInfo.FullName);
                                if (!clientID.StartsWith(this.txtIDNumber.Text))
                                {
                                    return null;
                                }
                            }
                            if (!String.IsNullOrWhiteSpace(this.txtMobile.Text))
                            {
                                var mobile = OperateIniFile.ReadIniData("Personal data", "mobile", "", fileInfo.FullName);
                                if (!this.txtMobile.Text.Equals(mobile))
                                {
                                    return null;
                                }
                            }
                        }
                    }                    
                }
                else
                {
                    return null;
                }
            }            
            return folderItem;
        }        

        private void btnReset_Click(object sender, RoutedEventArgs e)
        {            
            this.txtSurName.Text = "";
            this.txtGivenName.Text = "";
            this.txtMobile.Text = "";
            this.txtCode.Text = "";
            this.txtIDNumber.Text = "";
            this.txtSurName.Focus();

        }

        //扫码枪扫码结果处理
        private void Listener_ScanerEvent(ScanerHook.ScanerCodes codes)
        {
            var scanText = codes.Result;
            if (!string.IsNullOrEmpty(scanText))
            {
                var strArr = scanText.Split('|');
                if (strArr.Length >= 1)
                {
                    //Regex regChina = new Regex("^[^\x00-\xFF]");
                    Regex regEnglish = new Regex("^[a-zA-Z]+\\s*[a-zA-Z]*\\s*[a-zA-Z]*$");
                    var uname = strArr[0].Trim();
                    //用正则表达示判断是否为英文名
                    if (regEnglish.IsMatch(uname))
                    {
                        var nameArr = uname.Split(' ');
                        if (nameArr.Length > 1)
                        {
                            txtSurName.Text = nameArr[1];
                            txtGivenName.Text = nameArr[0];
                        }
                        else
                        {
                            txtSurName.Text = uname;
                            txtGivenName.Text = ".";
                        }
                    }
                    else
                    {
                        if (uname.Length > 1)
                        {
                            //如果是复姓
                            if (Constant.SurNameList.Contains(uname.Substring(0, 2)))
                            {
                                txtSurName.Text = uname.Substring(0, 2);
                                txtGivenName.Text = uname.Substring(2);
                            }
                            else
                            {
                                txtSurName.Text = uname.Substring(0, 1);
                                txtGivenName.Text = uname.Substring(1);
                            }
                        }
                        else
                        {
                            txtSurName.Text = uname;
                        }
                    }

                }
                if (strArr.Length >= 3)
                {
                    var idNumber = strArr[2].Trim();
                    txtIDNumber.Text = idNumber;                    
                }
                if (strArr.Length >= 5)
                {
                    txtMobile.Text = strArr[4].Trim();
                }
            }
            //触发按钮点击事件
            btnSearch.RaiseEvent(new RoutedEventArgs(Button.ClickEvent));            
        }  
        
    }
}
