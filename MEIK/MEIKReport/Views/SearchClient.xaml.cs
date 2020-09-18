using MEIK.Common;
using MEIKReport.Common;
using MEIKReport.Model;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
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
    
    public partial class SearchClient : Window
    {
        private ScanerHook listener = new ScanerHook();  
        private ObservableCollection<Person> patientCollection;
        public SearchClient()
        {
            InitializeComponent();
            listener.ScanerEvent += Listener_ScanerEvent;
            listener.Start();
            this.txtSurName.Focus();                        
        }

        private void btnSearch_Click(object sender, RoutedEventArgs e)
        {
            if (string.IsNullOrEmpty(this.txtSurName.Text) && string.IsNullOrEmpty(this.txtGivenName.Text) && string.IsNullOrEmpty(this.txtIDNumber.Text)
                && string.IsNullOrEmpty(this.txtMobile.Text) && string.IsNullOrEmpty(this.txtCode.Text))
            {
                MessageBox.Show(this, App.Current.FindResource("Message_87").ToString());
                this.txtSurName.Focus();
            }            
            else
            {                
                try
                {                    
                    NameValueCollection nvlist = new NameValueCollection();
                    nvlist.Add("code", this.txtCode.Text);
                    nvlist.Add("lastName", this.txtSurName.Text);
                    nvlist.Add("firstName", this.txtGivenName.Text);
                    nvlist.Add("idNumber", this.txtIDNumber.Text);
                    nvlist.Add("mobile", this.txtMobile.Text);

                    string resStr = HttpWebTools.Post(App.reportSettingModel.CloudPath + "/api/searchDataList", nvlist, App.reportSettingModel.CloudToken);
                    var jsonObj = JObject.Parse(resStr);
                    bool status = (bool)jsonObj["status"];
                    List<Person> personList = new List<Person>();
                    if (status)
                    {                        
                        var personArr = jsonObj["data"] as JArray;
                        foreach (JObject personItem in personArr)
                        {
                            Person person = new Person();                            
                            //获取User表的ID值                            
                            person.Id = Convert.ToInt32((string)personItem["userId"]);                            
                            person.Code = (string)personItem["code"];
                            person.SurName = (string)personItem["lastName"];
                            person.GivenName = (string)personItem["firstName"];
                            person.OtherName = (string)personItem["otherName"];
                            person.FullName = (string)personItem["clientName"];
                            //person.FullName = person.SurName;
                            //if (!string.IsNullOrEmpty(person.GivenName))
                            //{
                            //    person.FullName = person.SurName + "," + person.GivenName;
                            //}
                            //if (!string.IsNullOrEmpty(person.OtherName))
                            //{
                            //    person.FullName = person.FullName + " " + person.OtherName;
                            //}
                            
                            person.Mobile = (string)personItem["mobile"];
                            person.Email = (string)personItem["email"];
                            person.ScreenVenue = (string)personItem["location"];
                            person.ClientID = (string)personItem["idNumber"];                            
                            personList.Add(person);
                        }
                        
                    }
                    patientCollection = new ObservableCollection<Person>(personList);
                }
                catch(Exception ex) {
                    MessageBox.Show(this, ex.Message);
                }
                finally
                {
                    patientGrid.ItemsSource = patientCollection;
                }
            }
        }

        /// <summary>
        /// 下载数据
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void DG_Hyperlink_Click(object sender, RoutedEventArgs e)
        {
            if (patientGrid.SelectedItem == null)
            {
                return;
            }
            string patientFolder = null;
            try
            {
                var person = patientGrid.SelectedItem as Person;
                string monthFolder = App.reportSettingModel.DataBaseFolder + System.IO.Path.DirectorySeparatorChar + "20" + person.Code.Substring(0, 2) + "_" + person.Code.Substring(2, 2);
                if (!Directory.Exists(monthFolder))
                {
                    Directory.CreateDirectory(monthFolder);
                }

                patientFolder = monthFolder + System.IO.Path.DirectorySeparatorChar + person.Code + "-" + person.SurName;
                if (!string.IsNullOrEmpty(person.GivenName))
                {
                    patientFolder = patientFolder + "," + person.GivenName;
                }
                if (!string.IsNullOrEmpty(person.OtherName))
                {
                    patientFolder = patientFolder + " " + person.OtherName;
                }
                if (!Directory.Exists(patientFolder))
                {
                    string filename = person.Code + "-" + person.SurName;
                    filename = filename + (string.IsNullOrEmpty(person.GivenName) ? "" : "," + person.GivenName) + (string.IsNullOrEmpty(person.OtherName) ? "" : " " + person.OtherName) + ".zip";
                    //从服务器下载数据文件                                    
                    NameValueCollection postDict = new NameValueCollection();
                    postDict.Add("userId", person.Id.ToString());
                    var resPath = HttpWebTools.DownloadFile(App.reportSettingModel.CloudPath + "/api/downloadDataByUser", patientFolder, filename, postDict, App.reportSettingModel.CloudToken, 900000);
                    if (resPath == null)
                    {
                        MessageBox.Show(this, App.Current.FindResource("Message_40").ToString());

                    }
                    else
                    {
                        MessageBox.Show(this, App.Current.FindResource("Message_37").ToString());
                    }

                }
                else
                {
                    MessageBox.Show(this, String.Format(App.Current.FindResource("Message_24").ToString(), patientFolder));
                }
            }
            catch (Exception ex)
            {
                FileHelper.SetFolderPower(patientFolder, "Everyone", "FullControl");
                FileHelper.SetFolderPower(patientFolder, "Users", "FullControl");
                MessageBox.Show(this, App.Current.FindResource("Message_23").ToString());
                MessageBox.Show(this, string.Format(App.Current.FindResource("Message_25").ToString(), patientFolder, ", Error: " + ex.Message));
            }            
        }        
                
        private void btnReset_Click(object sender, RoutedEventArgs e)
        {            
            this.txtSurName.Text = "";
            this.txtGivenName.Text = "";
            this.txtMobile.Text = "";
            this.txtIDNumber.Text = "";
            this.txtCode.Text = "";
            if (patientCollection != null)
            {
                patientCollection.Clear();
            }
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
