   M S B A M L   `   `   `  ����   
MEIKReport    MEIKReport.AddFolderPage   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35^ ZWindowsFormsIntegration, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml       � Icon$/  )/MEIKReport;component/Images/App_Icon.png�� � Title$ Create Client��$	��335��$	��310�� � ShowInTaskbar .   � 
ResizeMode$ NoResize=� � Topmost .  � WindowStartupLocation$ CenterOwner=� � WindowStyle$ 
ToolWindow=�.��� ����  �� EndPoint$ 0.5,1&� �� 
StartPoint$ 0.5,0&�.��� 	 � Color$	 White��� $	 	#FFC4DBFB��
 � Offset$
 1Q�� $	 White��$
 0.543Q� � RowDefinitions �� $��5 ��� $��10 ��� $��30 ��� $��30 ��� $��30 ��� $��30 ��� $��25 ��� $��30 ��� $��30 ��� $��30 ��� $��50 ��� $��* � � ColumnDefinitions �� $	��100 ��� $��* �.+��� $��1��$��2��$
��Left=�$��Center=�.+��� $��14(�$��28��$��Bottom=�$
��Bold"�   Dialog1#���   $y�	Collapsed=��� $��2��$��Right=�$��Center=�.+��� $��14(�$��28��  Dialog2#���  �� $��2��$��1��$
��Left=�$��Center=�.+��� -   $��
txtSurName��$	��180��$��28��$��14(����	#FF3E6E8F�� $��3��$��Right=�$��Center=�.+��� $��14(�$��28��  Dialog3#���  �� $��3��$��1��$
��Left=�$��Center=�.+��� -   $��txtGivenName��$	��180��$��28��$��14(����	#FF3E6E8F�� $��4��$��Right=�$��Center=�.+��� $��14(�$��28��  Dialog9#���  �� $��4��$��1��$
��Left=�$��Center=�.+��� -   $��	txtMobile��$	��180��$��28��$��14(����	#FF3E6E8F�� $��5��$��Right=�$��Center=�.+��� $��14(�$��28��  Dialog12#���  �� $��5��$��1��$
��Left=�$��Center=�.+��� -   $��txtClientID��$	��180��$��28��$��14(����	#FF3E6E8F� $��6��$��1��$��Center=�.+��� $��60�� �� TextAlignment$ Center=�  UserDataPersonal7#���  �� $��60��$ Center=�  UserDataPersonal8#���  �� $��60��$ Center=�  UserDataPersonal9#���  �� $��7��$��0��$
��Auto��$��Right=�$��Center=�  UserDataPersonal4#���  $��0,0,3,0q�� $��7��$��1��$��Center=�.+��� -   $��txtBirthDate��$��52��$��Center=����	#FF3E6E8F����  �� Path$ 	BirthDate��� $��15��$��Center=�$ Center=�/�� -   $��txtBirthMonth��$��52��$��Center=����	#FF3E6E8F���� $ 
BirthMonth��� $��15��$��Center=�$ Center=�/�� -   $��txtBirthYear��$��52��$��Center=����	#FF3E6E8F���� $ 	BirthYear��� $��8��$��Right=�$��Center=�.+��� $��14(�$��28�� 	 Dialog5#��� 	 �� $��8��$��1��$
��Left=�$��Center=�.+��� -   $��txtPatientCode��$	��180��$��28��$��14(����	#FF3E6E8F{�.  �� $��9��$��Right=�$��Center=�.+��� $��14(� 
 Dialog8#��[
 �� $��9��$��1��$
��Left=�$��Center=�.+��� -	   $��listTechnicianName��$��26��$	��180��  NewComboBoxStyle#��[ $ �� IsSynchronizedWithCurrentItem$
 TrueY�$��14(�
����������	#FF3E6E8F���� .R���  �� Orientation$ 
Horizontal=�.+��� ���� $
 Name�� $��9��$y�	Collapsed=�.+��� $��14(�$��28��$	��120��$��Right=�$��Center=����	#FF3E6E8F.��
License:�� -
   $��technicianLicense��$	��220��$��22��{�.  ����  �� ElementName$ listTechnicianName��$ SelectedItem.License�� $��10��$��1��$��Center=�$��Center=�$��0,5,20,0q�.+��� -   $��btnAdd��$��80��$��26��  Dialog6#���  �� -   $��	btnCancel��$��80��$��26��$��10,0,0,0q�  Dialog7#���  