using MEIKReport.Common;
using MEIKReport.Views;
using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Forms.Integration;
using System.Windows.Input;
using System.Windows.Interop;
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
    public partial class MainWindow : Window
    {        
        //定义将要运行的原始的MEIK程序的进程
        public Process AppProc { get; set; }
        //定义将要运行的原始的MEIK Screen程序的进程
        public Process AppScreenProc { get; set; }
        //public IntPtr splashWinHwnd = IntPtr.Zero;
        protected MouseHook mouseHook = new MouseHook();
        protected KeyboardHook keyboardHook = new KeyboardHook();  
        //private WindowListen windowListen = new WindowListen();               
        
        
        public MainWindow()
        {
            string local = OperateIniFile.ReadIniData("Base", "Language", "zh-CN", System.AppDomain.CurrentDomain.BaseDirectory + "Config.ini");
            if (string.IsNullOrEmpty(local))
            {
                local = "zh-CN";
            }
            if (File.Exists(App.meikScreenFolder + System.IO.Path.DirectorySeparatorChar+ "Language.CHN"))
            {                
                App.strScreening = "筛选";
                App.strDiagnostics = "诊断";
                App.strExit = "退出";
                App.strStart = "开始";
                local = "zh-CN";
            }            

            App.local = local;
            if ("zh-CN".Equals(local))
            {
                App.Current.Resources.MergedDictionaries.Remove(new ResourceDictionary() { Source = new Uri(@"/Resources/StringResource.xaml", UriKind.RelativeOrAbsolute) });                
                App.Current.Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(@"/Resources/StringResource.zh-CN.xaml", UriKind.RelativeOrAbsolute) });
            }
            else
            {
                App.Current.Resources.MergedDictionaries.Remove(new ResourceDictionary() { Source = new Uri(@"/Resources/StringResource.zh-CN.xaml", UriKind.RelativeOrAbsolute) });                
                App.Current.Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(@"/Resources/StringResource.xaml", UriKind.RelativeOrAbsolute) });
            }
            InitializeComponent();            
            this.Visibility = Visibility.Collapsed;
        }
        
               
        private void Window_Loaded(object sender, RoutedEventArgs e)
        {                        
            StartApp();
            mouseHook.MouseUp += new System.Windows.Forms.MouseEventHandler(mouseHook_MouseUp);
            btnReport_Click(null, null);
            //启用键盘钩子
            //keyboardHook.KeyDown += new System.Windows.Forms.KeyEventHandler(keyboardHook_KeyDown);
            //keyboardHook.Start();            
            //添加全局消息过滤方法
            //GlobalMouseHandler globalClick = new GlobalMouseHandler();
            //Application.AddMessageFilter(globalClick);
        }

        /// <summary>
        /// 启用鼠标钩子
        /// </summary>
        public void StartMouseHook(){
            //启动鼠标钩子            
            mouseHook.Start();
        }
        /// <summary>
        /// 停止鼠标钩子
        /// </summary>
        public void StopMouseHook()
        {
            mouseHook.Stop();
        }

        public void StartApp()
        {
            try
            {
                App.splashWinHwnd = Win32Api.FindWindowEx(IntPtr.Zero, IntPtr.Zero, "TfmSplash", null);
                if (Win32Api.IsWindow(App.splashWinHwnd))//MEIK程序已经启动时
                {
                    btnExit_Click(null, null);
                }
                //StartMeik();                
            }
            catch (Exception ex)
            {
                MessageBox.Show("System Exception: " + ex.Message);
            }
        }

        private void StartMeikReport()
        {            
            string meikPath = App.meikReportFolder + System.IO.Path.DirectorySeparatorChar + "MEIKMD.exe";            
            bool meikExists = File.Exists(meikPath);            
            if (meikExists)
            {
                try
                {
                    //启动外部程序
                    AppProc = Process.Start(meikPath);
                    if (AppProc != null)
                    {
                        AppProc.StartInfo.WindowStyle = ProcessWindowStyle.Hidden; //隐藏
                        //proc.WaitForExit();//等待外部程序退出后才能往下执行
                        AppProc.WaitForInputIdle();
                        /**这段代码是把其他程序的窗体嵌入到当前窗体中**/
                        IntPtr appWinHandle = AppProc.MainWindowHandle;
                        App.splashWinHwnd = Win32Api.FindWindowEx(IntPtr.Zero, IntPtr.Zero, "TfmSplash", null);
                        //监视进程退出
                        //AppProc.EnableRaisingEvents = true;
                        //指定退出事件方法
                        //AppProc.Exited += new EventHandler(proc_Exited);
                        //设置程序嵌入到当前窗体
                        Win32Api.MoveWindow(App.splashWinHwnd, 0, 0, 720, 576, true);
                        // Set new process's parent to this window   
                        Win32Api.SetParent(App.splashWinHwnd, meikPanel.Handle);
                        //Add WS_CHILD window style to child window 
                        const int GWL_STYLE = -16;
                        const int WS_CHILD = 0x40000000;
                        int style = Win32Api.GetWindowLong(App.splashWinHwnd, GWL_STYLE);
                        style = style | WS_CHILD;
                        Win32Api.SetWindowLong(App.splashWinHwnd, GWL_STYLE, style);

                        ////把WPF窗口句柄转换为对象，但只针对WPF窗体
                        //HwndSource hwndSource = HwndSource.FromHwnd(splashWinHwnd);
                        //Window wnd = hwndSource.RootVisual as Window;
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(App.Current.FindResource("Message_1").ToString() +" "+ ex.Message);
                }                
            }
            else
            {
                MessageBox.Show(App.Current.FindResource("Message_91").ToString());
            }
        }

        private void StartMeikScreen()
        {
            string meikMAPath = App.meikScreenFolder + System.IO.Path.DirectorySeparatorChar + "MEIKMA.exe";
            bool meikExists = File.Exists(meikMAPath);
            if (meikExists)
            {
                try
                {
                    //启动外部程序
                    AppScreenProc = Process.Start(meikMAPath);
                    if (AppScreenProc != null)
                    {
                        //AppScreenProc.StartInfo.WindowStyle = ProcessWindowStyle.Hidden; //隐藏
                        //proc.WaitForExit();//等待外部程序退出后才能往下执行
                        AppScreenProc.WaitForInputIdle();
                        /**这段代码是把其他程序的窗体嵌入到当前窗体中**/
                        //IntPtr appWinHandle = AppScreenProc.MainWindowHandle;
                        //App.screenWinHwnd = Win32Api.FindWindowEx(IntPtr.Zero, IntPtr.Zero, "TfmMain", null);                                                                        
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(App.Current.FindResource("Message_1").ToString() + " " + ex.Message);
                }
            }
            else
            {
                MessageBox.Show("Failed to run MEIK Screen program, file is missing or corrupt.");
            }
        }

        //打开screen主界面
        public void StartScreen(string archiveFolder)
        {
            try
            {                
                //判斷是否有MEIK的Screen主介面
                //App.screenWinHwnd = Win32Api.FindWindowEx(IntPtr.Zero, IntPtr.Zero, "TfmMain", null);
                //if (Win32Api.IsWindow(App.screenWinHwnd))//MEIK程序已经启动时
                //{
                //    try
                //    {
                //        IntPtr exitBtnHwnd = Win32Api.FindWindowEx(App.screenWinHwnd, IntPtr.Zero, null, App.strExit);
                //        Win32Api.SendMessage(exitBtnHwnd, Win32Api.WM_CLICK, 0, 0);
                //    }
                //    catch { }
                //}

                OperateIniFile.WriteIniData("Base", "Patients base", archiveFolder, App.meikScreenIniFilePath);
                StartMeikScreen();

                //IntPtr screeningBtnHwnd = Win32Api.FindWindowEx(App.splashWinHwnd, IntPtr.Zero, null, App.strScreening);
                //Win32Api.SendMessage(screeningBtnHwnd, Win32Api.WM_CLICK, 0, 0);

            }
            catch (Exception ex)
            {
                MessageBox.Show("System Exception: " + ex.Message);
            }
        }

        //打开Diagnostics主界面
        public void StartDiagnostics(string archiveFolder)
        {
            try
            {
                
                ////判斷是否有MEIK的Screen主介面
                //App.diagnosticsWinHwnd = Win32Api.FindWindowEx(IntPtr.Zero, IntPtr.Zero, "TfmMain", null);
                //if (Win32Api.IsWindow(App.diagnosticsWinHwnd))//MEIK程序已经启动时
                //{
                //    try
                //    {
                //        IntPtr exitBtnHwnd = Win32Api.FindWindowEx(App.diagnosticsWinHwnd, IntPtr.Zero, null, App.strExit);
                //        Win32Api.SendMessage(exitBtnHwnd, Win32Api.WM_CLICK, 0, 0);
                //    }
                //    catch { }
                //}
                OperateIniFile.WriteIniData("Base", "Patients base", archiveFolder, App.meikReportIniFilePath);
                StartMeikReport();
                
                IntPtr diagnosticsBtnHwnd = Win32Api.FindWindowEx(App.splashWinHwnd, IntPtr.Zero, null, App.strDiagnostics);
                Win32Api.SendMessage(diagnosticsBtnHwnd, Win32Api.WM_CLICK, 0, 0);

            }
            catch (Exception ex)
            {
                MessageBox.Show("System Exception: " + ex.Message);
            }
        }

        private void Window_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            base.DragMove();            
        }

        //private void btnScreening_Loaded(object sender, RoutedEventArgs e)
        //{
        //    Button button = (Button)sender;
        //    button.Focus();
        //}

        //private void btnScreening_Click(object sender, RoutedEventArgs e)
        //{
        //    try
        //    {
        //        IntPtr screeningBtnHwnd = Win32Api.FindWindowEx(App.splashWinHwnd, IntPtr.Zero, null, App.strScreening);
        //        Win32Api.SendMessage(screeningBtnHwnd, Win32Api.WM_CLICK, 0, 0);
        //        this.StartMouseHook();
        //        this.Visibility = Visibility.Hidden;
        //    }
        //    catch (Exception ex)
        //    {
        //        MessageBox.Show("System Exception: "+ex.Message);
        //    }
            
        //}

        //private void btnDiagnostics_Click(object sender, RoutedEventArgs e)
        //{
        //    try
        //    {
        //        IntPtr diagnosticsBtnHwnd = Win32Api.FindWindowEx(App.splashWinHwnd, IntPtr.Zero, null, App.strDiagnostics);
        //        Win32Api.SendMessage(diagnosticsBtnHwnd, Win32Api.WM_CLICK, 0, 0);
        //        this.StartMouseHook();
        //        this.Visibility = Visibility.Hidden;
        //    }
        //    catch (Exception ex)
        //    {
        //        MessageBox.Show("System Exception: " + ex.Message);
        //    }
                 
        //}         

        private void btnReport_Click(object sender, RoutedEventArgs e)
        {
            this.Visibility = Visibility.Hidden;
            UserList userList = new UserList();
            userList.Owner = this;
            userList.Show();

        }

        private void btnExit_Click(object sender, RoutedEventArgs e)
        {            
            exitMeik();
        }

        public void exitMeik()
        {            
            try
            {
                IntPtr exitBtnHwnd = IntPtr.Zero;
                //判斷是否有MEIK的Screen主介面
                //App.screenWinHwnd = Win32Api.FindWindowEx(IntPtr.Zero, IntPtr.Zero, "TfmMain", null);
                if (AppScreenProc!=null&&!AppScreenProc.HasExited)//MEIK程序已经启动时
                {
                    try
                    {
                        //var exitStr=App.strExit;                        
                        //exitBtnHwnd = Win32Api.FindWindowEx(App.screenWinHwnd, IntPtr.Zero, null, App.strExit);
                        //IntPtr btnPtr=Win32Api.SetFocus(exitBtnHwnd);
                        //Win32Api.SendMessage(exitBtnHwnd, Win32Api.WM_CLICK, 0, 0);
                        AppScreenProc.Kill();                        
                    }
                    catch { }
                }
                if (AppProc != null && !AppProc.HasExited)
                {
                    AppProc.Kill();
                    //App.diagnosticsWinHwnd = Win32Api.FindWindowEx(meikPanel.Handle, IntPtr.Zero, "TfmMain", null);
                    //if (App.diagnosticsWinHwnd != IntPtr.Zero)
                    //{
                    //    exitBtnHwnd = Win32Api.FindWindowEx(App.diagnosticsWinHwnd, IntPtr.Zero, null, App.strExit);
                    //    Win32Api.SendMessage(exitBtnHwnd, Win32Api.WM_CLICK, 0, 0);
                    //}
                    //exitBtnHwnd = Win32Api.FindWindowEx(App.splashWinHwnd, IntPtr.Zero, null, App.strExit);
                    //Win32Api.SendMessage(exitBtnHwnd, Win32Api.WM_CLICK, 0, 0);
                    //AppProc = null;
                }
                

            }
            catch (Exception)
            {
                this.Close();
            }
        }

        /// <summary>
        ///启动外部程序退出事件
        /// </summary>
        void proc_Exited(object sender, EventArgs e)
        {
            this.Dispatcher.Invoke(new Action(delegate { this.Close(); }));            
        }      

        /// <summary>
        /// 鼠标按下的钩子回调方法
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void mouseHook_MouseUp(object sender, System.Windows.Forms.MouseEventArgs e)
        {
            if (e.Button == System.Windows.Forms.MouseButtons.Left)
            {
                IntPtr exitButtonHandle= Win32Api.WindowFromPoint(e.X, e.Y);
                IntPtr winHandle = Win32Api.GetParent(exitButtonHandle);                
                if (Win32Api.GetParent(winHandle) == AppProc.MainWindowHandle)
                {                    
                    //如果鼠标点击了EXIT按钮
                    StringBuilder winText = new StringBuilder(512);
                    Win32Api.GetWindowText(exitButtonHandle, winText, winText.Capacity);
                    if (App.strExit.Equals(winText.ToString(), StringComparison.OrdinalIgnoreCase))
                    {
                        if (App.opendWin != null)
                        {
                            App.opendWin.WindowState = WindowState.Maximized;                                                    
                        }
                        else
                        {
                            this.Visibility = Visibility.Visible;                            
                        }
                        this.StartMouseHook();
                    }
                }                
            }                        
        }

        /// <summary>
        /// 键盘按下的钩子回调方法
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void keyboardHook_KeyDown(object sender, System.Windows.Forms.KeyEventArgs e)
        {
            MessageBox.Show(sender.GetType().ToString());
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            if (AppProc!=null && !AppProc.HasExited)
            {
                AppProc.Kill();                
            }
            if (AppScreenProc != null && !AppScreenProc.HasExited)
            {
                AppScreenProc.Kill();
            }
        }

        //private void btnSetting_Click(object sender, RoutedEventArgs e)
        //{
        //    this.Visibility = Visibility.Hidden;
        //    ReportSettingPage reportSettingPage = new ReportSettingPage();
        //    reportSettingPage.Owner = this;
        //    reportSettingPage.Show();
        //}
                
    }
}
