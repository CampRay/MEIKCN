   M S B A M L   `   `   `  ���� E  AMEIKReport, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null5clr-namespace:MEIKReport.CommonMEIKReport.Common  "    MEIKReport.ReportSettingPage   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35^ ZWindowsFormsIntegration, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     /cvtclr-namespace:MEIKReport.Common     -     � Title$  System Parameters��$	��710��$	��440�� � Icon$ /Images/App_Icon.png�� � ShowInTaskbar .   � 
ResizeMode$ NoResize=� � WindowStartupLocation$ CenterScreen=� � Topmost .  � WindowStyle$ 
ToolWindow=� � 	Resources ,   &MEIKReport.Common.EnumBooleanConverter   enumBooleanConverter��%   &	          .��� ����  �� EndPoint$ 0.5,1&�	 �� 
StartPoint$	 0.5,0&�.��� 
 � Color$
 White��� $
 	#FFC4DBFB�� � Offset$ 1Q�� $
 White��$ 0.543Q� � RowDefinitions �� $��1 ��� $��* �.+�� $��1�� �� $	��370 ��� $��50 �.+��� $��0��$��10q�.+��� -   $��
tabSetting�� �� SelectedIndex$ 0�����	#FF3E6E8F��Z� .@��� -   $��	tabReport��  SettingReportTabTitle#��[   NewTabButtonStyle#��[ $��14(�$	��150��$��26��$��Center=����	#FF768795
�������.���  �� $	��150 ��� $��38 ��� $��38 ��� $��75 ��� $��* �.+��� -   $��grpUserProfile��$��0��$��10q�   SettingReportNameGroupHeader#��[ ���	#FF3E6E8F���	#FF3E6E8F.���  �� $��25 ��� $��32 ��� $��32 ��� $��5 ��� $��* �.+�� $��0��$��90,0,0,0q�$��Bottom=�.+��� $��25��$	��190��$��13(�$��Center=�  SettingReportFullName#��[ ���	#FF3E6E8F�� $��25��$	��180��$��13(�$��Center=�  SettingReportLicense#��[ ���	#FF3E6E8F� -   $��optTech��$��1��$��2q�$
��Left=�.+��� $��28��$��90��$��14(�$��Right=�$��Center=�  SettingReportTechNameLab#��[ ���	#FF3E6E8F�� -   $��listTechnicianName��  NewComboBoxStyle#��[ $��0��$��0��$��26��$	��190��$��Center=�$ �� IsSynchronizedWithCurrentItem$
 TrueY�$��12(�
����������	#FF3E6E8F���� *	TechNames+���� .R���  �� Orientation$ 
Horizontal=�.+��� ����  �� Path$
 Name��� -   $��technicianLicense��$	��180��$��28��$��Center=�$��5,0,0,0q�{�.  ���	#FF3E6E8F
�����������  �� ElementName$ listTechnicianName��$ SelectedItem.License��� -   $��
btnAddTech��$��70��$��28��$��5,0,0,0q�$��Center=�  CommonAddBtn#��[ �� -	   $��
btnDelTech��$��70��$��28��$��5,0,0,0q�$��Center=� 	 CommonDeleteBtn#��[	 � -
   $��	optDoctor��$��2��$��2q�$
��Left=�.+��� $��28��$��90��$��14(�$��Right=�$��Center=� 
 SettingReportDoctorNameLab#��[
 ���	#FF3E6E8F�� -   $��listDoctorName��#��[ $��0��$��0��$��26��$	��190��$��Center=�$
 TrueY�$��14(�
����������	#FF3E6E8F���� *DoctorNames+���� .R��� $ 
Horizontal=�.+��� ���� $
 Name��� -   $��doctorLicense��$	��180��$��28��$��Center=�$��5,0,0,0q�{�.  ���	#FF3E6E8F
����������� $ listDoctorName��$ SelectedItem.License��� -   $��btnAddDoctor��$��70��$��28��$��5,0,0,0q�$��Center=�#��[ �� -   $��btnDelDoctor��$��70��$��28��$��5,0,0,0q�$��Center=�#��[	 �� $ 
Horizontal=�$��3��$��90,0,0,0q�$��Center=�$y�	Collapsed=�.+��� -   $��chkHideDoctor��  SettingReportDoctorRequired#��[ ���	#FF3E6E8F���	#FF3E6E8F$��1q�$��20,0,0,0q� d� 	IsChecked �� *ShowDoctorSignature+�� -   $��chkHideTech��$y�	Collapsed=�  SettingReportTechRequired#��[ ���	#FF3E6E8F���	#FF3E6E8F$��1q�$��20,0,0,0q� �� *ShowTechSignature+� $��1��$��Center=�$��10,0,0,0q�.+��� $��28��$	��110��$��14(�$��Right=�  SettingDeviceId#��[ ���	#FF3E6E8F�� -   $��txtDeviceId��$��14(�$	��190��$��28�����	#FF3E6E8F���� *
DeviceNo+� $��2��$��Center=�$��10,0,0,0q�.+��� $��28��$	��110��$��14(�$��Right=�  SettingScreenVenue#��[ ���	#FF3E6E8F�� -   $��txtScreenVenue��$��14(�$	��400��$��28�����	#FF3E6E8F���� *ScreenVenue+�� $��3��$��10q� $  SettingReportTransferGroupHeader#��[ ���	#FF3E6E8F���	#FF3E6E8F.���  � ColumnDefinitions �� $��* ��� $��* ��� $��* �.+��� $��0��$��0��$��	15,10,0,0q�$
��Left=�.+�	�  	� 	GroupName$ radTransferMode��$��14(�$��22��$	��200�����	#FF3E6E8F���	#FF3E6E8F$��1q� �� *TransferMode+ �� 	Converter# [   �� ConverterParameter$ Email��.��Email�� $��1��$��0,10,0,0q�$
��Left=�.+�	� $ radTransferMode��$��14(�$��22��$	��300�����	#FF3E6E8F���	#FF3E6E8F$��1q� �� *TransferMode+# [  $ CloudServer��.��Cloud Server�� $��4��$��10q� ! SettingReportPrintGroupHeader#��[ ���	#FF3E6E8F���	#FF3E6E8F$y�	Collapsed=�.���  �� $��* ��� $��* �.+��� $��0��$��0��$��	15,10,0,0q�$
��Left=�.+�	� $ radPrintPaper��$��14(�$��22��$	��300�����	#FF3E6E8F���	#FF3E6E8F$��1q� �� *
PrintPaper+# [  $ Letter��.��Letter (8.5" x 11.0")�� $��1��$��0,10,0,0q�$
��Left=�.+�	� $ radPrintPaper��$��14(�$��22��$	��300�����	#FF3E6E8F���	#FF3E6E8F$��1q� �� *
PrintPaper+# [  $ A4��.��A4 (8.27" x 11.69")�� -   $��tabMail��  SettingEmailTabTitle#��[ #��[ $��14(�$	��150��$��26��$��Center=����	#FF768795
�������.���  �� $��40 ��� $��40 ��� $��40 ��� $��40 ��� $��40 ��� $��40 ��� $��40 � �� $	��135 ��� $��* ��� $	��135 ��� $��* �.+��� $��0��$��0��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailHost#��[ ���	#FF3E6E8F�� $��0��$��1��$��5q�$
��Left=�.+��� -   $��txtMailHost��$��14(�$	��190��$��28������ *
MailHost+�� $��0��$��2��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailPort#��[ ���	#FF3E6E8F� $��0��$��3��$��5q�$
��Left=�$��Center=�.+��� -   $��txtMailPort��$��14(�$��50��$��28������ *
MailPort+�� -   $��
txtMailSsl��$��20,0,0,0q�$��28��$��80��$��14(�  SettingEmailSSL#��[ ���	#FF3E6E8F���	#FF3E6E8F$��1q� �� *	MailSsl+�� $��1��$��0��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailUsername#��[ ���	#FF3E6E8F�� $��1��$��1��$��5q�$
��Left=�.+��� -   $��txtMailUsername��$��14(�$	��190��$��28������ *MailUsername+�� $��1��$��2��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailPassword#��[ ���	#FF3E6E8F�� $��1��$��3��$��5q�$
��Left=�.+�G� -   $��
txtMailPwd��$��14(�$	��190��$��28���� $��2��$��0��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailFrom#��[ ���	#FF3E6E8F�� $��2��$��1��$��5q�$
��Left=�.+��� -   $��txtMyMailAddress��$��14(�$	��190��$��28������ *MailAddress+�� $��3��$��0��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailTo#��[ ���	#FF3E6E8F�� $��3��$��1��$��5q�$
��Left=�.+��� -   $��txtToMailAddress��$y�	Collapsed=�$��14(�$	��190��$��28������ *MailAddress+�� -   $��listToMailAddress��#��[ $��0��$��0��$��26��$	��190��$��Center=�$
 TrueY�$��12(�
����������	#FF3E6E8F���� *ToMailAddressList+���� .R��� $ 
Horizontal=�.+��� ���� � $��3��$��2��$��2��$��Center=�.+��� -   $��btnAddEmail��$��70��$��28��$��5,0,0,0q�$��Center=�#��[ �� -   $��btnDelEmail��$��70��$��28��$��5,0,0,0q�$��Center=�#��[	 �� $��4��$��0��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailSubject#��[ ���	#FF3E6E8F�� $��4��$��1��$��3��$��5q�$
��Left=�.+��� -   $��txtMailSubject��$��28��$	��510��$��14(����� *MailSubject+�� $��5��$��0��$��5q�.+��� $��28��$
��Auto��$��14(�$��Right=�  SettingEmailBody#��[ ���	#FF3E6E8F�� $��5��$��1��$��2��$��3��$��5,0,0,0q�$
��Left=�.+��� -   $��txtMailBody�� �� TextWrapping$
 Wrap=� �� AcceptsReturn . " �� VerticalScrollBarVisibility$ Visible=�$��80��$	��510��$��14(����� *
MailBody+�� -    $��tabImage��  SettingCompanyTitle#��[ #��[ $��14(�$	��150��$��26��$��Center=����	#FF768795
�������.���  �� $��10 ��� $��40 ��� $��40 ��� $	��200 ��� $��20 � �� $	��150 ��� $	��510 �.+���   SettingCompanyName#��[ $��1��$��Center=�$��Right=�$
��Bold"����	#FF3E6E8F� $��1��$��1��$��Center=�$
��Left=�.+��� -!   $��txtCompanyName��$��1��$��1��$	��450�����	#FF3E6E8F���� *CompanyName+��   SettingUploadLogo#��[ $��2��$��Center=�$��Right=�$
��Bold"����	#FF3E6E8F� $��2��$��1��$��Center=�$
��Left=�.+��� -"   $��txtLogoPath��$	��250�����	#FF3E6E8F�� -#   $��logoSelectBtn��$��26��$	��100��  NewButtonStyle#��[   SettingLogoSelect#��[ �� -$   $��logoDefaultBtn��$��26��$	��100��#��[    SettingLogoDefault#��[  �� -%   $��logoImg��$��3��$��2��$��/Images/title.png��$��10q��� -&   $��tabIcon��$y�	Collapsed=�#��[ #��[ $��14(�$	��150��$��26��$��Center=����	#FF768795
�������.���  �� $��10 ��� $��40 ��� $	��210 ��� $��20 � �� $	��200 ��� $	��460 �.+���  ! SettingUploadIcon#��[! $��1��$��Center=�$��Right=�$
��Bold"����	#FF3E6E8F� $��1��$��1��$
��Left=�$��Center=�.+��� -'   $��listDeciceNo��#��[ $��0��$��0��$��26��$	��190��$��Center=�$
 TrueY�$��14(�
����������	#FF3E6E8F���� *
DeciceLogo+���� .R��� $ 
Horizontal=�.+��� ���� $ Device��� -(   $��btnAddDeviceLogo��$��70��$��28��$��5,0,0,0q�$��Center=�#��[ �� -)   $��btnDelDeviceLogo��$��70��$��28��$��5,0,0,0q�$��Center=�#��[	 � $��2��$��2�� �� $��30 ��� $	��170 ��� $��* � �� $	��280 ��� $	��190 ��� $	��190 �.+���  " SettingCompanyInfo#��[" $��0��$��Bottom=�$��Center=��� #��[ $��1��$��Bottom=�$��Center=���  # SettingSealIcon#��[# $��2��$��Bottom=�$��Center=��� $��1��$��0��$	��168��$	��270��$
 Wrap=�$ Visible=�$��Center=�$	��Top=����� $ listDeciceNo��$ SelectedItem.Address��� -*   $��IconImg��$��1��$��1��$��10q���Z� �� -+   $��SealImg��$��1��$��2��$��10q���Z� � $��1��$��0,0,10,0q�$��Right=�.+��� -,   $��btnReportSettingSave��$��30��$��14(�$	��100�� $ CommonSaveBtn#��[$ �� --   $��btnReportSettingClose��$��30��$��14(�$	��100��$��10,0,0,0q� % CommonCloseBtn#��[% � -.   $��progressBarGrid��$��0��$��2��$y�	Collapsed=�.+��� ���	#7F000000� -/   $��uploadProgressBar��$	��500��$��20��
����������	#FF68FF00$��0q� � Value$ 0Q���Z� 