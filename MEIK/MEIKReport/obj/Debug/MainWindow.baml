   M S B A M L   `   `   `  ����^  ZWindowsFormsIntegration, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35tOclr-namespace:System.Windows.Forms.Integration;assembly=WindowsFormsIntegration System.Windows.Forms.Integration  5   	   [ WSystem.Windows.Forms, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089Y@clr-namespace:System.Windows.Forms;assembly=System.Windows.FormsSystem.Windows.Forms 5   	    
MEIKReport   MEIKReport.MainWindow   5   	   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation      5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   _wfiOclr-namespace:System.Windows.Forms.Integration;assembly=WindowsFormsIntegration     5   	   Owf@clr-namespace:System.Windows.Forms;assembly=System.Windows.Forms     5   	   -   5          � Icon$/  )/MEIKReport;component/Images/App_Icon.png��5   	    � Title$ MEIK CN��5   	   $��1��6   $��1��6$    � WindowStyle$
 None=�6.    � 
ResizeMode$ NoResize=�6B    � WindowStartupLocation$ CenterScreen=�5   	    � ShowInTaskbar .  $y�	Collapsed=�6�   ��5   X   Z� ��6n   Z�  � 	Resources 5       �� 
TargetType   ButtonFocusVisual �� Property	 �� Value  NewButtonStyle
 �� Triggers P� Property P� Value �� 
TargetType ��Name �� Color'  !System.Windows.VisualStateManager   VisualStateGroups%  System.Windows.VisualStateGroup   Transitions%  System.Windows.VisualTransition	   To   GeneratedDuration   States   System.Windows.VisualState   
Storyboard �� 
TargetName �� TargetProperty	 �� To��%x  &	        &	 J     �� 5   
   " ��6   .�5      ��  � ��
	 �����6+   5      �� 5   
   " ��6   
 5      P� 5       � �� . . �5      ��  � ��
	 �����65   5      P� 5       �@J�IsMouseOver . . �5      ��  � ��
	 �����65   5      P� 5       �@J�IsKeyboardFocused . . �5      ��  � ��
	 �����65   5      .�5      ��  � ��	 5      �� 5      " ��6&   .R�5      �� $ RootElement��6!   $��1q�66   ��5       �� 5!   "   BorderBrush��$ Black��6G   ._�5$      � $��0q�6#   #��z��6.   .+�5%   "   �� #��z��63   $��Center=�6X   $��Center=�6u   5&       5'        5(   "   CommonStates�� 5)   &     5*   *   $ 	MouseOver��6;   $ 0:0:0D�6J   . 5,   &     
Normal��62     5-   &   	MouseOver��. 5.   *   �� .��5/   .   �� $ BorderBrush��6=   $ Color�6a   $ WhiteX�6�   50   +   51   '     52   &   Pressed��. 53   *   �� .��54   .   �� $ BorderBrush��6=   $ Color�6a   $ WhiteX�6�   55   +   56   '   57   #     58   "   FocusStates��. 59   &     
Foused��. 5:   *   �� .��5;   .   �� $ BorderBrush��6=   $ Color�6a   $ WhiteX�6�   5<   +   5=   '   5>   #   5@      5A      5C      �� 5D       � ��
	 �   �6+   �� 5E       � ��
	 �����6+   5G      5I      .��5J      � .+�5K   
   �� $y�	Collapsed=�6   .+�5L      7   1System.Windows.Forms.Integration.WindowsFormsHost  -   5          Name$ windwosFormsHost��5L   #   |�.  $y�	Collapsed=�6{      
Foreground 6=   Z�    
Background 6S   Z�    Child. 5M         System.Windows.Forms.Panel  "  System.Windows.Forms.Control   Width$ 0��6      Height$ 0��6%   -   5        	meikPanel��5M   0   5N      5O      5{      5|      