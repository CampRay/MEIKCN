   M S B A M L   `   `   `  ����  
MEIKReport    MEIKReport.OpenFolderPage   5   	   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35^ ZWindowsFormsIntegration, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	     � Icon$/  )/MEIKReport;component/Images/App_Icon.png��5   	    � Title$ Choose Archive Folder��5   	   $	��430��6'   $	��500��64    � ShowInTaskbar .   � 
ResizeMode$ NoResize=�6V    � Topmost .  � WindowStartupLocation$ CenterScreen=�6{    � WindowStyle$ 
ToolWindow=�6�   .��5      � ���	#FFEAF3FF � RowDefinitions 5   
   �� 5      $��10 �6   �� 5	      $��26 �6   �� 5
      $��26 �6   �� 5      $��26 �6   �� 5      $��* �6   �� 5      $��50 �6    � ColumnDefinitions 5   
   �� 5      $��10 �6   �� 5      $��* �6   �� 5      $��10 �6   .+�5   
   � $��1��6   $��1��6    5      �� 5      �� 5      .+�5      � 	 � Orientation$	 
Horizontal=�6   $��0��61   .+�5      �� $��80��6   $��Center=�6'   
 �� TextAlignment$
 Right=�6B      Dialog2#���   6X   6|   �� 5      -   5        $��
txtSurName��5      $	��150��6,   6;   5      � 5      $	 
Horizontal=�6   $��1��61   .+�5      �� $��80��6   $��Center=�6'   $
 Right=�6B     Dialog3#���  6X   6|   �� 5      -   5        $��txtGivenName��5      $	��150��6.   6=   5       5!      � 5"   
   $��2��6   $��1��6    5#      �� 5$      �� 5%      .+�5'      � $	 
Horizontal=�6   $��0��61   .+�5(      �� $��80��6   $��Center=�6'   $
 Right=�6B     Dialog12#���  6X   6}   �� 5)      -   5        $��txtIDNumber��5)      $	��150��6-   6<   5*      � 5+      $	 
Horizontal=�6   $��1��61   .+�5,      �� $��80��6   $��Center=�6'   $
 Right=�6B     Dialog9#���  6X   6|   �� 5-      -   5        $��	txtMobile��5-      $	��150��6+   6:   5.      5/      � 50   
   $��3��6   $��1��6    51      �� 52      �� 53      .+�55      � $	 
Horizontal=�6   $��0��61   .+�56      �� $��80��6   $��Center=�6'   $
 Right=�6B     Dialog5#���  6X   6|   �� 57      -   5        $��txtCode��57      $	��150��6)   68   58      � 59      $	 
Horizontal=�6   $��1��61   $��60,0,0,0q�6A   .+�5:      �� -   5        $��	btnSearch��5:      $��80��6*   $��22��65     CommonSearch#���  6Y   6�   �� 5;      -   5        $��btnReset��5;      $��80��6)   $��22��64   $��10,0,0,0q�6W     CommonReset#���  6i   6�   5<      5=      � 5>   
   $��4��6   $��1��6   $	��480��6,   $	��266��68   $��Center=�6E   
�������.+�5?      R� -   5        $��treeView��5?      $	��460��6'   $	��256��63   $��Center=�6Y   5@      � 5B   
   $��5��6   $��1��6    5C      �� 5D      �� 5E      .+�5G      ��  �� Orientation$ 
Horizontal=�6   $��0��62   $
��Left=�6B   $��10,0,0,0q�6]   .+�5H      �� -	   5        $��btnDel��5H      $��80��6'   $��26��62     CommonDeleteBtn#���  6>   6�   5I      �� 5J      $ 
Horizontal=�6   $��1��62   $��Right=�6B   $��0,0,10,0q�6^   .+�5K      �� -
   5        $��btnOpen��5K      $��80��6(   $��26��63     CommonArchiveBtn#���  6U   6�   �� 5L      -   5        $��	btnCancel��5L      $��80��6*   $��26��65   $��10,0,0,0q�6Y    	 Dialog7#��� 	 6k   6�   5M      5N      5O      5P      