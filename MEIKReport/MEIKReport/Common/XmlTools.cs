using MEIKReport.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.Windows;
using System.Xml;

namespace MEIKReport.Common
{
    public class XmlTools
    {
        public static ShortFormReport ReadXmlFile(string xmlFilePath)
        {

            if (FileHelper.FileInUsed(xmlFilePath))
            {
                return null;
            }
            ShortFormReport shortFormReport = new ShortFormReport();
            XmlDocument doc = new XmlDocument();            
            try
            {
                Encoding iso8859 = Encoding.GetEncoding("iso-8859-1");
                Encoding gb2312 = Encoding.GetEncoding("gb2312");

                doc.Load(xmlFilePath);                
                var nodeList=doc.GetElementsByTagName("CICL");
                var node=nodeList.Item(nodeList.Count - 1); 
                string dataMenstrualCycle = gb2312.GetString(iso8859.GetBytes(node.InnerText)); 
                
                shortFormReport.DataMeanElectricalConductivity3 = dataMenstrualCycle;
                shortFormReport.DataComparativeElectricalConductivity3 = dataMenstrualCycle;
                shortFormReport.DataDivergenceBetweenHistograms3 = dataMenstrualCycle;
                if (string.IsNullOrEmpty(dataMenstrualCycle))
                {
                    shortFormReport.DataMenstrualCycle = "0";
                }
                else if (dataMenstrualCycle.StartsWith("1 phase") || dataMenstrualCycle.StartsWith("phase 1") || dataMenstrualCycle.StartsWith("第一阶段") || dataMenstrualCycle.StartsWith("一期"))
                {
                    shortFormReport.DataMenstrualCycle = "1";// App.Current.FindResource("ReportContext_15").ToString();
                    shortFormReport.DataMeanElectricalConductivity3 = "";
                    shortFormReport.DataComparativeElectricalConductivity3 = "";
                    shortFormReport.DataDivergenceBetweenHistograms3 = "";
                }
                else if (dataMenstrualCycle.StartsWith("2 phase") || dataMenstrualCycle.StartsWith("phase 2") || dataMenstrualCycle.StartsWith("第二阶段") || dataMenstrualCycle.StartsWith("二期"))
                {
                    shortFormReport.DataMenstrualCycle = "2";//App.Current.FindResource("ReportContext_16").ToString();
                    shortFormReport.DataMeanElectricalConductivity3 = "";
                    shortFormReport.DataComparativeElectricalConductivity3 = "";
                    shortFormReport.DataDivergenceBetweenHistograms3 = "";
                }
                else if (dataMenstrualCycle.StartsWith("1 and 2 phase") || dataMenstrualCycle.StartsWith("第一和第二阶段") || dataMenstrualCycle.StartsWith("一期和二期"))
                {
                    shortFormReport.DataMenstrualCycle = "3";//App.Current.FindResource("ReportContext_17").ToString();
                }
                else if (dataMenstrualCycle.StartsWith("dysmenorrhea") || dataMenstrualCycle.StartsWith("痛经"))
                {
                    shortFormReport.DataMenstrualCycle = "4";// App.Current.FindResource("ReportContext_18").ToString();
                }
                else if (dataMenstrualCycle.StartsWith("missing")|| dataMenstrualCycle.StartsWith("失经"))
                {
                    shortFormReport.DataMenstrualCycle = "5";// App.Current.FindResource("ReportContext_19").ToString();
                    //shortFormReport.DataMeanElectricalConductivity3 = "1";
                    //shortFormReport.DataComparativeElectricalConductivity3 = "1";
                    //shortFormReport.DataDivergenceBetweenHistograms3 = "1";
                }
                else if (dataMenstrualCycle.StartsWith("pregnancy") || dataMenstrualCycle.StartsWith("妊娠") || dataMenstrualCycle.StartsWith("哺乳期"))
                {
                    shortFormReport.DataMenstrualCycle = "6";// App.Current.FindResource("ReportContext_20").ToString();
                    //shortFormReport.DataMeanElectricalConductivity3 = "2";
                    //shortFormReport.DataComparativeElectricalConductivity3 = "2";
                    //shortFormReport.DataDivergenceBetweenHistograms3 = "2";
                }
                else if (dataMenstrualCycle.StartsWith("lactation") || dataMenstrualCycle.StartsWith("哺乳期") || dataMenstrualCycle.StartsWith("乳汁分泌"))
                {
                    shortFormReport.DataMenstrualCycle = "7";// App.Current.FindResource("ReportContext_21").ToString();
                    //shortFormReport.DataMeanElectricalConductivity3 = "3";
                    //shortFormReport.DataComparativeElectricalConductivity3 = "3";
                    //shortFormReport.DataDivergenceBetweenHistograms3 = "3";
                }
                else if (dataMenstrualCycle.StartsWith("postmenopause") || dataMenstrualCycle.StartsWith("postmenopausal") || dataMenstrualCycle.StartsWith("绝经期"))
                {
                    shortFormReport.DataMenstrualCycle = "8";// App.Current.FindResource("ReportContext_21").ToString();
                    //shortFormReport.DataMeanElectricalConductivity3 = "1";
                    //shortFormReport.DataComparativeElectricalConductivity3 = "1";
                    //shortFormReport.DataDivergenceBetweenHistograms3 = "1";
                }
                
                //shortFormReport.DataMenstrualCycle = doc.FormFields[11].Result;

                nodeList = doc.GetElementsByTagName("GORM");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataHormones = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("PORAZH");
                node = nodeList.Item(nodeList.Count - 1);
                var skinAffections = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(skinAffections))
                {
                    shortFormReport.DataSkinAffections = "0";
                }
                else if (skinAffections.StartsWith("no")|| skinAffections.StartsWith("否"))
                {
                    shortFormReport.DataSkinAffections = "1";
                }
                else if (skinAffections.StartsWith("nevus") || skinAffections.StartsWith("痣"))
                {
                    shortFormReport.DataSkinAffections = "2";
                }
                else if (skinAffections.StartsWith("wart") || skinAffections.StartsWith("疣"))
                {
                    shortFormReport.DataSkinAffections = "3";
                }
                else if (skinAffections.StartsWith("acne") || skinAffections.StartsWith("痤疮"))
                {
                    shortFormReport.DataSkinAffections = "4";
                }
                else if (skinAffections.StartsWith("scar after surgery") || skinAffections.StartsWith("术后疤痕"))
                {
                    shortFormReport.DataSkinAffections = "5";
                }
                else if (skinAffections.StartsWith("consequences of burns") || skinAffections.StartsWith("烧伤后改变"))
                {
                    shortFormReport.DataSkinAffections = "6";
                }
                else if (skinAffections.StartsWith("sunburns") || skinAffections.StartsWith("晒伤"))
                {
                    shortFormReport.DataSkinAffections = "7";
                }
                else
                {
                    shortFormReport.DataSkinAffections = "0";
                }

                nodeList = doc.GetElementsByTagName("OIE_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftChangesOfElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftChangesOfElectricalConductivity))
                {
                    shortFormReport.DataLeftChangesOfElectricalConductivity = "0";
                }
                else if (leftChangesOfElectricalConductivity.StartsWith("no")||leftChangesOfElectricalConductivity.StartsWith("否"))
                {
                    shortFormReport.DataLeftChangesOfElectricalConductivity = "1";
                }
                else if (leftChangesOfElectricalConductivity.StartsWith("focal") || leftChangesOfElectricalConductivity.StartsWith("集中性"))
                {
                    shortFormReport.DataLeftChangesOfElectricalConductivity = "2";
                }
                else if (leftChangesOfElectricalConductivity.StartsWith("diffuse") || leftChangesOfElectricalConductivity.StartsWith("分散性"))
                {
                    shortFormReport.DataLeftChangesOfElectricalConductivity = "3";
                }
                
                nodeList = doc.GetElementsByTagName("OIE_R");
                node = nodeList.Item(nodeList.Count - 1);                
                var rightChangesOfElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightChangesOfElectricalConductivity))
                {
                    shortFormReport.DataRightChangesOfElectricalConductivity = "0";
                }
                else if (rightChangesOfElectricalConductivity.StartsWith("no") || rightChangesOfElectricalConductivity.StartsWith("否"))
                {
                    shortFormReport.DataRightChangesOfElectricalConductivity = "1";
                }
                else if (rightChangesOfElectricalConductivity.StartsWith("focal") || rightChangesOfElectricalConductivity.StartsWith("集中性"))
                {
                    shortFormReport.DataRightChangesOfElectricalConductivity = "2";
                }
                else if (rightChangesOfElectricalConductivity.StartsWith("diffuse") || rightChangesOfElectricalConductivity.StartsWith("分散性"))
                {
                    shortFormReport.DataRightChangesOfElectricalConductivity = "3";
                }

                nodeList = doc.GetElementsByTagName("SMZH_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftMammaryStruc = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftMammaryStruc))
                {
                    shortFormReport.DataLeftMammaryStruct = "0";
                }
                else if (leftMammaryStruc.StartsWith("not changed")|| leftMammaryStruc.StartsWith("无变化"))
                {
                    shortFormReport.DataLeftMammaryStruct = "1";
                }
                else if (leftMammaryStruc.StartsWith("changed")|| leftMammaryStruc.StartsWith("有变化"))
                {
                    shortFormReport.DataLeftMammaryStruct = "2";
                }

                nodeList = doc.GetElementsByTagName("SMZH_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightMammaryStruc = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightMammaryStruc))
                {
                    shortFormReport.DataRightMammaryStruct = "0";
                }
                else if (rightMammaryStruc.StartsWith("not changed") || rightMammaryStruc.StartsWith("无变化"))
                {
                    shortFormReport.DataRightMammaryStruct = "1";
                }
                else if (rightMammaryStruc.StartsWith("changed") || rightMammaryStruc.StartsWith("有变化"))
                {
                    shortFormReport.DataRightMammaryStruct = "2";
                }
                //shortFormReport.DataLeftMammaryStruct = doc.FormFields[17].Result;
                //shortFormReport.DataRightMammaryStruct = doc.FormFields[18].Result;

                nodeList = doc.GetElementsByTagName("ZMS_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftLactiferousSinusZone = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftLactiferousSinusZone))
                {
                    shortFormReport.DataLeftLactiferousSinusZone = "0";
                }
                else if (leftLactiferousSinusZone.StartsWith("not represented")||leftLactiferousSinusZone.StartsWith("不可见"))
                {
                    shortFormReport.DataLeftLactiferousSinusZone = "1";
                }
                else if (leftLactiferousSinusZone.StartsWith("not expanded") || leftLactiferousSinusZone.StartsWith("无扩张"))
                {
                    shortFormReport.DataLeftLactiferousSinusZone = "2";
                }
                else if (leftLactiferousSinusZone.StartsWith("expanded") || leftLactiferousSinusZone.StartsWith("扩张"))
                {
                    shortFormReport.DataLeftLactiferousSinusZone = "3";
                }
                else if (leftLactiferousSinusZone.StartsWith("fragmentated") || leftLactiferousSinusZone.StartsWith("分隔的"))
                {
                    shortFormReport.DataLeftLactiferousSinusZone = "4";
                }

                nodeList = doc.GetElementsByTagName("ZMS_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightLactiferousSinusZone = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightLactiferousSinusZone))
                {
                    shortFormReport.DataRightLactiferousSinusZone = "0";
                }
                else if (rightLactiferousSinusZone.StartsWith("not represented") || rightLactiferousSinusZone.StartsWith("不可见"))
                {
                    shortFormReport.DataRightLactiferousSinusZone = "1";
                }
                else if (rightLactiferousSinusZone.StartsWith("not expanded") || rightLactiferousSinusZone.StartsWith("无扩张"))
                {
                    shortFormReport.DataRightLactiferousSinusZone = "2";
                }
                else if (rightLactiferousSinusZone.StartsWith("expanded") || rightLactiferousSinusZone.StartsWith("扩张"))
                {
                    shortFormReport.DataRightLactiferousSinusZone = "3";
                }
                else if (rightLactiferousSinusZone.StartsWith("fragmentated") || rightLactiferousSinusZone.StartsWith("分隔的"))
                {
                    shortFormReport.DataRightLactiferousSinusZone = "4";
                }
                //shortFormReport.DataLeftLactiferousSinusZone = doc.FormFields[19].Result;
                //shortFormReport.DataRightLactiferousSinusZone = doc.FormFields[20].Result;

                nodeList = doc.GetElementsByTagName("KMZH_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftMammaryContour = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftMammaryContour))
                {
                    shortFormReport.DataLeftMammaryContour = "0";
                }
                else if (leftMammaryContour.StartsWith("preserved")||leftMammaryContour.StartsWith("无变化"))
                {
                    shortFormReport.DataLeftMammaryContour = "1";
                }
                else if (leftMammaryContour.StartsWith("thickened") || leftMammaryContour.StartsWith("变厚"))
                {
                    shortFormReport.DataLeftMammaryContour = "2";
                }
                else if (leftMammaryContour.StartsWith("deformed") || leftMammaryContour.StartsWith("变形"))
                {
                    shortFormReport.DataLeftMammaryContour = "3";
                }

                nodeList = doc.GetElementsByTagName("KMZH_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightMammaryContour = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightMammaryContour))
                {
                    shortFormReport.DataRightMammaryContour = "0";
                }
                else if (rightMammaryContour.StartsWith("preserved") || rightMammaryContour.StartsWith("无变化"))
                {
                    shortFormReport.DataRightMammaryContour = "1";
                }
                else if (rightMammaryContour.StartsWith("thickened") || rightMammaryContour.StartsWith("变厚"))
                {
                    shortFormReport.DataRightMammaryContour = "2";
                }
                else if (rightMammaryContour.StartsWith("deformed") || rightMammaryContour.StartsWith("变形"))
                {
                    shortFormReport.DataRightMammaryContour = "3";
                }
                //shortFormReport.DataLeftMammaryContour = doc.FormFields[21].Result;
                //shortFormReport.DataRightMammaryContour = doc.FormFields[22].Result;

                nodeList = doc.GetElementsByTagName("S_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftSegment = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("S_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightSegment = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //shortFormReport.DataLeftLocation = doc.FormFields[23].Result;
                //shortFormReport.DataRightLocation = doc.FormFields[24].Result;

                nodeList = doc.GetElementsByTagName("K_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftNumber = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftNumber))
                {
                    shortFormReport.DataLeftNumber = "0";
                }
                else if (leftNumber.StartsWith("1"))
                {
                    shortFormReport.DataLeftNumber = "1";
                }
                else if (leftNumber.StartsWith("2"))
                {
                    shortFormReport.DataLeftNumber = "2";
                }
                else if (leftNumber.StartsWith("3"))
                {
                    shortFormReport.DataLeftNumber = "3";
                }
                else if (leftNumber.StartsWith("numerous")|| leftNumber.StartsWith("多量"))
                {
                    shortFormReport.DataLeftNumber = "4";
                }

                nodeList = doc.GetElementsByTagName("K_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightNumber = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightNumber))
                {
                    shortFormReport.DataRightNumber = "0";
                }
                else if (rightNumber.StartsWith("1"))
                {
                    shortFormReport.DataRightNumber = "1";
                }
                else if (rightNumber.StartsWith("2"))
                {
                    shortFormReport.DataRightNumber = "2";
                }
                else if (rightNumber.StartsWith("3"))
                {
                    shortFormReport.DataRightNumber = "3";
                }
                else if (rightNumber.StartsWith("numerous") || rightNumber.StartsWith("多量"))
                {
                    shortFormReport.DataRightNumber = "4";
                }
                //shortFormReport.DataLeftNumber = doc.FormFields[25].Result;
                //shortFormReport.DataRightNumber = doc.FormFields[26].Result;

                nodeList = doc.GetElementsByTagName("R_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftSize = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("R_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightSize = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //shortFormReport.DataLeftSize = doc.FormFields[27].Result;
                //shortFormReport.DataRightSize = doc.FormFields[28].Result;

                nodeList = doc.GetElementsByTagName("F_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftShape = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftShape))
                {
                    shortFormReport.DataLeftShape = "0";
                }
                else if (leftShape.StartsWith("round")|| leftShape.StartsWith("圆形"))
                {
                    shortFormReport.DataLeftShape = "1";
                }
                else if (leftShape.StartsWith("oval") || leftShape.StartsWith("椭圆形"))
                {
                    shortFormReport.DataLeftShape = "2";
                }
                else if (leftShape.StartsWith("lobular") || leftShape.StartsWith("叶状"))
                {
                    shortFormReport.DataLeftShape = "3";
                }
                else if (leftShape.StartsWith("irregular") || leftShape.StartsWith("不规则"))
                {
                    shortFormReport.DataLeftShape = "4";
                }

                nodeList = doc.GetElementsByTagName("F_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightShape = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightShape))
                {
                    shortFormReport.DataRightShape = "0";
                }
                else if (rightShape.StartsWith("round") || rightShape.StartsWith("圆形"))
                {
                    shortFormReport.DataRightShape = "1";
                }
                else if (rightShape.StartsWith("oval") || rightShape.StartsWith("椭圆形"))
                {
                    shortFormReport.DataRightShape = "2";
                }
                else if (rightShape.StartsWith("lobular") || rightShape.StartsWith("叶状"))
                {
                    shortFormReport.DataRightShape = "3";
                }
                else if (rightShape.StartsWith("irregular") || rightShape.StartsWith("不规则"))
                {
                    shortFormReport.DataRightShape = "4";
                }
                //shortFormReport.DataLeftShape = doc.FormFields[29].Result;
                //shortFormReport.DataRightShape = doc.FormFields[30].Result;

                nodeList = doc.GetElementsByTagName("KWO_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftContourAroundFocus = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftContourAroundFocus))
                {
                    shortFormReport.DataLeftContourAroundFocus = "0";
                }
                else if (leftContourAroundFocus.StartsWith("missing")||leftContourAroundFocus.StartsWith("失经"))
                {
                    shortFormReport.DataLeftContourAroundFocus = "1";
                }
                else if (leftContourAroundFocus.StartsWith("distinct") || leftContourAroundFocus.StartsWith("清晰"))
                {
                    shortFormReport.DataLeftContourAroundFocus = "2";
                }
                else if (leftContourAroundFocus.StartsWith("hyperimpedance") || leftContourAroundFocus.StartsWith("阻抗超标的"))
                {
                    shortFormReport.DataLeftContourAroundFocus = "3";
                }
                else if (leftContourAroundFocus.StartsWith("indistinct") || leftContourAroundFocus.StartsWith("不清晰"))
                {
                    shortFormReport.DataLeftContourAroundFocus = "4";
                }

                nodeList = doc.GetElementsByTagName("KWO_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightContourAroundFocus = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightContourAroundFocus))
                {
                    shortFormReport.DataRightContourAroundFocus = "0";
                }
                else if (rightContourAroundFocus.StartsWith("missing") || rightContourAroundFocus.StartsWith("失经"))
                {
                    shortFormReport.DataRightContourAroundFocus = "1";
                }
                else if (rightContourAroundFocus.StartsWith("distinct") || rightContourAroundFocus.StartsWith("清晰"))
                {
                    shortFormReport.DataRightContourAroundFocus = "2";
                }
                else if (rightContourAroundFocus.StartsWith("hyperimpedance") || rightContourAroundFocus.StartsWith("阻抗超标的"))
                {
                    shortFormReport.DataRightContourAroundFocus = "3";
                }
                else if (rightContourAroundFocus.StartsWith("indistinct") || rightContourAroundFocus.StartsWith("不清晰"))
                {
                    shortFormReport.DataRightContourAroundFocus = "4";
                }
                //shortFormReport.DataLeftContourAroundFocus = doc.FormFields[31].Result;
                //shortFormReport.DataRightContourAroundFocus = doc.FormFields[32].Result;

                nodeList = doc.GetElementsByTagName("VES_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftInternalElectricalStructure = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftInternalElectricalStructure))
                {
                    shortFormReport.DataLeftInternalElectricalStructure = "0";
                }
                else if (leftInternalElectricalStructure.StartsWith("hyperimpedance")|| leftInternalElectricalStructure.StartsWith("高阻抗"))
                {
                    shortFormReport.DataLeftInternalElectricalStructure = "1";
                }
                else if (leftInternalElectricalStructure.StartsWith("isoimpedance") || leftInternalElectricalStructure.StartsWith("等阻抗"))
                {
                    shortFormReport.DataLeftInternalElectricalStructure = "2";
                }
                else if (leftInternalElectricalStructure.StartsWith("hypoimpedance") || leftInternalElectricalStructure.StartsWith("低阻抗"))
                {
                    shortFormReport.DataLeftInternalElectricalStructure = "3";
                }
                else if (leftInternalElectricalStructure.StartsWith("animpedance") || leftInternalElectricalStructure.StartsWith("无阻抗"))
                {
                    shortFormReport.DataLeftInternalElectricalStructure = "4";
                }

                nodeList = doc.GetElementsByTagName("VES_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightInternalElectricalStructure = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightInternalElectricalStructure))
                {
                    shortFormReport.DataRightInternalElectricalStructure = "0";
                }
                else if (rightInternalElectricalStructure.StartsWith("hyperimpedance") || rightInternalElectricalStructure.StartsWith("高阻抗"))
                {
                    shortFormReport.DataRightInternalElectricalStructure = "1";
                }
                else if (rightInternalElectricalStructure.StartsWith("isoimpedance") || rightInternalElectricalStructure.StartsWith("等阻抗"))
                {
                    shortFormReport.DataRightInternalElectricalStructure = "2";
                }
                else if (rightInternalElectricalStructure.StartsWith("hypoimpedance") || rightInternalElectricalStructure.StartsWith("低阻抗"))
                {
                    shortFormReport.DataRightInternalElectricalStructure = "3";
                }
                else if (rightInternalElectricalStructure.StartsWith("animpedance") || rightInternalElectricalStructure.StartsWith("无阻抗"))
                {
                    shortFormReport.DataRightInternalElectricalStructure = "4";
                }
                //shortFormReport.DataLeftInternalElectricalStructure = doc.FormFields[33].Result;
                //shortFormReport.DataRightInternalElectricalStructure = doc.FormFields[34].Result;

                nodeList = doc.GetElementsByTagName("OTIPK_L");
                node = nodeList.Item(nodeList.Count - 1);
                var leftSurroundingTissues = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(leftSurroundingTissues))
                {
                    shortFormReport.DataLeftSurroundingTissues = "0";
                }
                else if (leftSurroundingTissues.StartsWith("preserved")||leftSurroundingTissues.StartsWith("无变化"))
                {
                    shortFormReport.DataLeftSurroundingTissues = "1";
                }
                else if (leftSurroundingTissues.StartsWith("structure distubance") || leftSurroundingTissues.StartsWith("结构紊乱"))
                {
                    shortFormReport.DataLeftSurroundingTissues = "2";
                }
                else if (leftSurroundingTissues.StartsWith("structure displacement") || leftSurroundingTissues.StartsWith("结构移位"))
                {
                    shortFormReport.DataLeftSurroundingTissues = "3";
                }
                else if (leftSurroundingTissues.StartsWith("thikening") || leftSurroundingTissues.StartsWith("增厚"))
                {
                    shortFormReport.DataLeftSurroundingTissues = "4";
                }
                else if (leftSurroundingTissues.StartsWith("extrusion") || leftSurroundingTissues.StartsWith("挤压膨胀"))
                {
                    shortFormReport.DataLeftSurroundingTissues = "5";
                }
                else if (leftSurroundingTissues.StartsWith("retraction") || leftSurroundingTissues.StartsWith("收缩"))
                {
                    shortFormReport.DataLeftSurroundingTissues = "6";
                }

                nodeList = doc.GetElementsByTagName("OTIPK_R");
                node = nodeList.Item(nodeList.Count - 1);
                var rightSurroundingTissues = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(rightSurroundingTissues))
                {
                    shortFormReport.DataRightSurroundingTissues = "0";
                }
                else if (rightSurroundingTissues.StartsWith("preserved") || rightSurroundingTissues.StartsWith("无变化"))
                {
                    shortFormReport.DataRightSurroundingTissues = "1";
                }
                else if (rightSurroundingTissues.StartsWith("structure distubance") || rightSurroundingTissues.StartsWith("结构紊乱"))
                {
                    shortFormReport.DataRightSurroundingTissues = "2";
                }
                else if (rightSurroundingTissues.StartsWith("structure displacement") || rightSurroundingTissues.StartsWith("结构移位"))
                {
                    shortFormReport.DataRightSurroundingTissues = "3";
                }
                else if (rightSurroundingTissues.StartsWith("thikening") || rightSurroundingTissues.StartsWith("增厚"))
                {
                    shortFormReport.DataRightSurroundingTissues = "4";
                }
                else if (rightSurroundingTissues.StartsWith("extrusion") || rightSurroundingTissues.StartsWith("挤压膨胀"))
                {
                    shortFormReport.DataRightSurroundingTissues = "5";
                }
                else if (rightSurroundingTissues.StartsWith("retraction") || rightSurroundingTissues.StartsWith("收缩"))
                {
                    shortFormReport.DataRightSurroundingTissues = "6";
                }
                //shortFormReport.DataLeftSurroundingTissues = doc.FormFields[35].Result;
                //shortFormReport.DataRightSurroundingTissues = doc.FormFields[36].Result;



                nodeList = doc.GetElementsByTagName("SE1_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftMeanElectricalConductivity1 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("SE1_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightMeanElectricalConductivity1 = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("SE2_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftMeanElectricalConductivity2 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("SE2_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightMeanElectricalConductivity2 = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                
                //ShortFormReport.DataMeanElectricalConductivity3 = doc.FormFields[41].Result;

                nodeList = doc.GetElementsByTagName("SE_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftMeanElectricalConductivity3 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("SE_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightMeanElectricalConductivity3 = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("SRE1");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftComparativeElectricalConductivity1 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //ShortFormReport.DataRightComparativeElectricalConductivity1 = doc.FormFields[44].Result;
                nodeList = doc.GetElementsByTagName("SRE2");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftComparativeElectricalConductivity2 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //ShortFormReport.DataRightComparativeElectricalConductivity2 = doc.FormFields[45].Result;
                nodeList = doc.GetElementsByTagName("SRE0");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftComparativeElectricalConductivity3 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //ShortFormReport.DataRightComparativeElectricalConductivity3 = doc.FormFields[46].Result;

                nodeList = doc.GetElementsByTagName("RG1");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftDivergenceBetweenHistograms1 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //ShortFormReport.DataRightDivergenceBetweenHistograms1 = doc.FormFields[47].Result;
                nodeList = doc.GetElementsByTagName("RG2");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftDivergenceBetweenHistograms2 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //ShortFormReport.DataRightDivergenceBetweenHistograms2 = doc.FormFields[48].Result;
                nodeList = doc.GetElementsByTagName("RG0");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftDivergenceBetweenHistograms3 = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //ShortFormReport.DataRightDivergenceBetweenHistograms3 = doc.FormFields[49].Result;

                nodeList = doc.GetElementsByTagName("FE_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftPhaseElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("FE_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightPhaseElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("RAST");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataAgeElectricalConductivityReference = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                
                nodeList = doc.GetElementsByTagName("VE_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftAgeElectricalConductivity= gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //string dataLeftAgeElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //if (string.IsNullOrEmpty(dataLeftAgeElectricalConductivity))
                //{
                //    shortFormReport.DataLeftAgeElectricalConductivity = "0";// "";
                //}
                //else if (dataLeftAgeElectricalConductivity.StartsWith("<5"))
                //{
                //    shortFormReport.DataLeftAgeElectricalConductivity = "1";// App.Current.FindResource("ReportContext_111").ToString();
                //}
                //else if (dataLeftAgeElectricalConductivity.StartsWith(">95"))
                //{
                //    shortFormReport.DataLeftAgeElectricalConductivity = "3";// App.Current.FindResource("ReportContext_113").ToString();
                //}
                //else 
                //{
                //    shortFormReport.DataLeftAgeElectricalConductivity = "2";// App.Current.FindResource("ReportContext_112").ToString();
                //}
                ////ShortFormReport.DataLeftAgeElectricalConductivity = doc.FormFields[57].Result;
                nodeList = doc.GetElementsByTagName("VE_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightAgeElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //string dataRightAgeElectricalConductivity = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                //if (string.IsNullOrEmpty(dataRightAgeElectricalConductivity))
                //{
                //    shortFormReport.DataRightAgeElectricalConductivity = "0";// "";
                //}
                //else if (dataRightAgeElectricalConductivity.StartsWith("<5"))
                //{
                //    shortFormReport.DataRightAgeElectricalConductivity = "1";// App.Current.FindResource("ReportContext_111").ToString();
                //}
                //else if (dataRightAgeElectricalConductivity.StartsWith(">95"))
                //{
                //    shortFormReport.DataRightAgeElectricalConductivity = "3";// App.Current.FindResource("ReportContext_113").ToString();
                //}
                //else
                //{
                //    shortFormReport.DataRightAgeElectricalConductivity = "2";// App.Current.FindResource("ReportContext_112").ToString();
                //}
                ////ShortFormReport.DataRightAgeElectricalConductivity = doc.FormFields[58].Result;

                nodeList = doc.GetElementsByTagName("ZAKL");
                node = nodeList.Item(nodeList.Count - 1);
                string dataExamConclusion = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(dataExamConclusion))
                {
                    shortFormReport.DataExamConclusion = "0";//"";
                }
                else if (dataExamConclusion.StartsWith("Pubertal Period") || dataExamConclusion.StartsWith("青春期"))
                {
                    shortFormReport.DataExamConclusion = "1";// App.Current.FindResource("ReportContext_116").ToString();
                }
                else if (dataExamConclusion.StartsWith("Early reproductive period") || dataExamConclusion.StartsWith("育龄早期") || dataExamConclusion.StartsWith("早生育期"))
                {
                    shortFormReport.DataExamConclusion = "2";// App.Current.FindResource("ReportContext_117").ToString();
                }
                else if (dataExamConclusion.StartsWith("Reproductive period") || dataExamConclusion.StartsWith("育龄期") || dataExamConclusion.StartsWith("生育期"))
                {
                    shortFormReport.DataExamConclusion = "3";// App.Current.FindResource("ReportContext_118").ToString();
                }
                else if (dataExamConclusion.StartsWith("Perimenopause period") || dataExamConclusion.StartsWith("围绝经期") || dataExamConclusion.StartsWith("绝经期间"))
                {
                    shortFormReport.DataExamConclusion = "4";// App.Current.FindResource("ReportContext_119").ToString();
                }
                else if (dataExamConclusion.StartsWith("Postmenopause period") || dataExamConclusion.StartsWith("Postmenopause period") || dataExamConclusion.StartsWith("绝经期后"))
                {
                    shortFormReport.DataExamConclusion = "5";// App.Current.FindResource("ReportContext_120").ToString();
                }
                
                //ShortFormReport.DataExamConclusion = doc.FormFields[59].Result
                nodeList = doc.GetElementsByTagName("TIP_L");
                node = nodeList.Item(nodeList.Count - 1);
                string dataLeftMammaryGland = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(dataLeftMammaryGland))
                {
                    shortFormReport.DataLeftMammaryGland = "0";// "";
                }
                else if (dataLeftMammaryGland.StartsWith("Ductal type") || dataLeftMammaryGland.StartsWith("导管型乳腺结构") || dataLeftMammaryGland.StartsWith("导管式结构"))
                {
                    shortFormReport.DataLeftMammaryGland = "5";// App.Current.FindResource("ReportContext_126").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Mixed structure with a") || dataLeftMammaryGland.StartsWith("混合型，导管型结构优势") || dataLeftMammaryGland.StartsWith("导管成分优先的混合式结构"))
                {
                    shortFormReport.DataLeftMammaryGland = "4";// App.Current.FindResource("ReportContext_125").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Mixed type of structure") || dataLeftMammaryGland.StartsWith("混合型乳腺结构") || dataLeftMammaryGland.StartsWith("混合式结构"))
                {
                    shortFormReport.DataLeftMammaryGland = "3";// App.Current.FindResource("ReportContext_124").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Mixed with a predominance") || dataLeftMammaryGland.StartsWith("混合型，无定型结构优势") ||  dataLeftMammaryGland.StartsWith("无形态成分优先的混合式结构"))
                {
                    shortFormReport.DataLeftMammaryGland = "2";// App.Current.FindResource("ReportContext_123").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Amorphous type") || dataLeftMammaryGland.StartsWith("无定型乳腺结构") || dataLeftMammaryGland.StartsWith("无形态式结构"))
                {
                    shortFormReport.DataLeftMammaryGland = "1";// App.Current.FindResource("ReportContext_122").ToString();
                }
                //ShortFormReport.DataLeftMammaryGland = doc.FormFields[60].Result;
                nodeList = doc.GetElementsByTagName("VE_GRANITSI_L");
                node = nodeList.Item(nodeList.Count - 1);
                string dataLeftAgeRelated = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                shortFormReport.DataLeftAgeElectricalConductivity = node.InnerText;
                if (string.IsNullOrEmpty(dataLeftAgeRelated))
                {
                    shortFormReport.DataLeftAgeRelated = "0";//"";
                }
                else if (dataLeftAgeRelated.StartsWith("<5"))
                {
                    shortFormReport.DataLeftAgeRelated = "1";// App.Current.FindResource("ReportContext_111").ToString();
                }
                else if (dataLeftAgeRelated.StartsWith(">95"))
                {
                    shortFormReport.DataLeftAgeRelated = "3";// App.Current.FindResource("ReportContext_113").ToString();
                }
                else
                {
                    shortFormReport.DataLeftAgeRelated = "2";// App.Current.FindResource("ReportContext_112").ToString();
                }
                //ShortFormReport.DataLeftAgeRelated = doc.FormFields[61].Result;

                nodeList = doc.GetElementsByTagName("VE_ZAKL_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftMammaryGlandResult = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("ZAKL_L");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataLeftBiRadsCategory = gb2312.GetString(iso8859.GetBytes(node.InnerText));


                nodeList = doc.GetElementsByTagName("TIP_R");
                node = nodeList.Item(nodeList.Count - 1);
                string dataRightMammaryGland = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(dataLeftMammaryGland))
                {
                    shortFormReport.DataRightMammaryGland = "0";// "";
                }
                else if (dataLeftMammaryGland.StartsWith("Ductal type") || dataLeftMammaryGland.StartsWith("导管型乳腺结构") || dataLeftMammaryGland.StartsWith("导管式结构"))
                {
                    shortFormReport.DataRightMammaryGland = "5";// App.Current.FindResource("ReportContext_126").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Mixed structure with a") || dataLeftMammaryGland.StartsWith("混合型，导管型结构优势") || dataLeftMammaryGland.StartsWith("导管成分优先的混合式结构"))
                {
                    shortFormReport.DataRightMammaryGland = "4";// App.Current.FindResource("ReportContext_125").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Mixed type of structure") || dataLeftMammaryGland.StartsWith("Mixed type of structure") || dataLeftMammaryGland.StartsWith("混合型乳腺结构") || dataLeftMammaryGland.StartsWith("混合式结构"))
                {
                    shortFormReport.DataRightMammaryGland = "3";// App.Current.FindResource("ReportContext_124").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Mixed with a predominance") || dataLeftMammaryGland.StartsWith("混合型，无定型结构优势") || dataLeftMammaryGland.StartsWith("无形态成分优先的混合式结构"))
                {
                    shortFormReport.DataRightMammaryGland = "2";// App.Current.FindResource("ReportContext_123").ToString();
                }
                else if (dataLeftMammaryGland.StartsWith("Amorphous type") || dataLeftMammaryGland.StartsWith("无定型乳腺结构") || dataLeftMammaryGland.StartsWith("无形态式结构"))
                {
                    shortFormReport.DataRightMammaryGland = "1";// App.Current.FindResource("ReportContext_122").ToString();
                }
                //ShortFormReport.DataLeftMammaryGland = doc.FormFields[60].Result;
                nodeList = doc.GetElementsByTagName("VE_GRANITSI_R");
                node = nodeList.Item(nodeList.Count - 1);
                string dataRightAgeRelated = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                shortFormReport.DataRightAgeElectricalConductivity = node.InnerText;
                if (string.IsNullOrEmpty(dataRightAgeRelated))
                {
                    shortFormReport.DataRightAgeRelated = "0";
                }
                else if (dataRightAgeRelated.StartsWith("<5"))
                {
                    shortFormReport.DataRightAgeRelated = "1";
                }
                else if (dataRightAgeRelated.StartsWith(">95"))
                {
                    shortFormReport.DataRightAgeRelated = "3";
                }
                else
                {
                    shortFormReport.DataRightAgeRelated = "2";
                }

                nodeList = doc.GetElementsByTagName("VE_ZAKL_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightMammaryGlandResult = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                nodeList = doc.GetElementsByTagName("ZAKL_R");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRightBiRadsCategory= gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("BAL");
                node = nodeList.Item(nodeList.Count - 1);
                string totalPts= gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(totalPts))
                {
                    shortFormReport.DataTotalPts = "0";
                }
                else
                {
                    shortFormReport.DataTotalPts = totalPts.Substring(0,1);
                }

                nodeList = doc.GetElementsByTagName("BIEIM_KATEG");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataPoint = gb2312.GetString(iso8859.GetBytes(node.InnerText));


                nodeList = doc.GetElementsByTagName("BIEIM_DIAGNOZ_S");
                node = nodeList.Item(nodeList.Count - 1);
                var categoryId = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(categoryId))
                {
                    shortFormReport.DataBiRadsCategory = "0";
                }
                else if (categoryId.StartsWith("No pathological changes detected") || categoryId.StartsWith("未发现病理性变化"))
                {
                    shortFormReport.DataBiRadsCategory = "1";
                }
                else if (categoryId.StartsWith("Benign lesion") || categoryId.StartsWith("良性变化"))
                {
                    shortFormReport.DataBiRadsCategory = "2";
                }
                else if (categoryId.StartsWith("Probably benign findings") || categoryId.StartsWith("可能良性发现"))
                {
                    shortFormReport.DataBiRadsCategory = "3";
                }
                else if (categoryId.StartsWith("Suspicious abnormality") || categoryId.StartsWith("可疑"))
                {
                    shortFormReport.DataBiRadsCategory = "4";
                }
                else if (categoryId.StartsWith("Highly suggestive malignancy") || categoryId.StartsWith("高度可疑"))
                {
                    shortFormReport.DataBiRadsCategory = "5";
                }
                
                nodeList = doc.GetElementsByTagName("BIEIM_DIAGNOZ_T");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataConclusion = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("RECOM_T");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataRecommendation = gb2312.GetString(iso8859.GetBytes(node.InnerText));

                nodeList = doc.GetElementsByTagName("RECOM_S");
                node = nodeList.Item(nodeList.Count - 1);
                string furtherExam = gb2312.GetString(iso8859.GetBytes(node.InnerText));
                if (string.IsNullOrEmpty(furtherExam))
                {
                    shortFormReport.DataFurtherExam = "0";
                }
                else if (furtherExam.StartsWith("Routine mammography")|| furtherExam.StartsWith("常规乳腺摄片"))
                {
                    shortFormReport.DataFurtherExam = "1";
                }
                else if (furtherExam.StartsWith("Re-examination in 6 months")|| furtherExam.StartsWith("六个月后复查"))
                {
                    shortFormReport.DataFurtherExam = "2";
                }
                else if (furtherExam.StartsWith("Biopsy")|| furtherExam.StartsWith("活检"))
                {
                    shortFormReport.DataFurtherExam = "3";
                }

                nodeList = doc.GetElementsByTagName("RECOM_T");
                node = nodeList.Item(nodeList.Count - 1);
                shortFormReport.DataComments= gb2312.GetString(iso8859.GetBytes(node.InnerText));
                                
            }
            catch(Exception){}
            
            return shortFormReport;
        }

        public static void WriteXmlFile(string xmlFilePath, ShortFormReport shortFormReport)
        {

            if (FileHelper.FileInUsed(xmlFilePath))
            {
                return;
            }            
            XmlDocument doc = new XmlDocument();
            try
            {
                Encoding iso8859 = Encoding.GetEncoding("iso-8859-1");
                Encoding gb2312 = Encoding.GetEncoding("gb2312");

                doc.Load(xmlFilePath);
                var nodeList = doc.GetElementsByTagName("CICL");
                var node = nodeList.Item(nodeList.Count - 1);
                node.InnerText =HttpUtility.HtmlEncode(GB2312ToISO8859(shortFormReport.DataMenstrualCycle));                

                nodeList = doc.GetElementsByTagName("GORM");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataHormones);                

                nodeList = doc.GetElementsByTagName("PORAZH");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataSkinAffections);                

                nodeList = doc.GetElementsByTagName("OIE_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftChangesOfElectricalConductivity);
                

                nodeList = doc.GetElementsByTagName("OIE_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightChangesOfElectricalConductivity);
                
                nodeList = doc.GetElementsByTagName("SMZH_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftMammaryStruct);
                

                nodeList = doc.GetElementsByTagName("SMZH_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightMammaryStruct);
                
                nodeList = doc.GetElementsByTagName("ZMS_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftLactiferousSinusZone);
                

                nodeList = doc.GetElementsByTagName("ZMS_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightLactiferousSinusZone);
                
                nodeList = doc.GetElementsByTagName("KMZH_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftMammaryContour);
                

                nodeList = doc.GetElementsByTagName("KMZH_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightMammaryContour);
                

                nodeList = doc.GetElementsByTagName("S_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftSegment);
                
                nodeList = doc.GetElementsByTagName("S_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightSegment);
                
                nodeList = doc.GetElementsByTagName("K_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftNumber);
                

                nodeList = doc.GetElementsByTagName("K_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightNumber);
                

                nodeList = doc.GetElementsByTagName("R_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftSize);
                
                nodeList = doc.GetElementsByTagName("R_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightSize);
                
                nodeList = doc.GetElementsByTagName("F_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftShape);
                

                nodeList = doc.GetElementsByTagName("F_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightShape);
                

                nodeList = doc.GetElementsByTagName("KWO_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftContourAroundFocus);
                

                nodeList = doc.GetElementsByTagName("KWO_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightContourAroundFocus);
                
                nodeList = doc.GetElementsByTagName("VES_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftInternalElectricalStructure);
                
                nodeList = doc.GetElementsByTagName("VES_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightInternalElectricalStructure);

                
                nodeList = doc.GetElementsByTagName("OTIPK_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftSurroundingTissues);

                
                nodeList = doc.GetElementsByTagName("OTIPK_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightSurroundingTissues);
                

                
                nodeList = doc.GetElementsByTagName("ZAKL");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataExamConclusion);
                                
                nodeList = doc.GetElementsByTagName("TIP_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftMammaryGland);

                
                
                nodeList = doc.GetElementsByTagName("VE_GRANITSI_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftAgeRelated);
                                                
                nodeList = doc.GetElementsByTagName("VE_ZAKL_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftMammaryGlandResult);
                
                nodeList = doc.GetElementsByTagName("ZAKL_L");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataLeftBiRadsCategory);                


                nodeList = doc.GetElementsByTagName("TIP_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightMammaryGland);

                
                nodeList = doc.GetElementsByTagName("VE_GRANITSI_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightAgeRelated);
                

                nodeList = doc.GetElementsByTagName("VE_ZAKL_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightMammaryGlandResult);
                
                nodeList = doc.GetElementsByTagName("ZAKL_R");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRightBiRadsCategory);
                
                nodeList = doc.GetElementsByTagName("BAL");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataTotalPts);
                                

                nodeList = doc.GetElementsByTagName("BIEIM_DIAGNOZ_S");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataBiRadsCategory);

                
                nodeList = doc.GetElementsByTagName("BIEIM_DIAGNOZ_T");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataConclusion);
                
                nodeList = doc.GetElementsByTagName("RECOM_T");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataRecommendation);
                
                nodeList = doc.GetElementsByTagName("RECOM_S");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataFurtherExam);
                

                nodeList = doc.GetElementsByTagName("RECOM_T");
                node = nodeList.Item(nodeList.Count - 1);
                node.InnerText = GB2312ToISO8859(shortFormReport.DataComments);
                doc.Save(xmlFilePath);
            }
            catch (Exception) { }
            
        }

        public static string GB2312ToISO8859(string str)
        {
            try
            {
                Encoding iso8859 = Encoding.GetEncoding("iso-8859-1");
                Encoding gb2312 = Encoding.GetEncoding("gb2312");                                              
                byte[] temp = gb2312.GetBytes(str);
                //转换不正确，不能转成&#xC8;&#xD1;&#xC9;&#xEF;格式
                byte[] temp1 = Encoding.Convert(gb2312, iso8859, temp);
                string result = iso8859.GetString(temp1);
                return result;
            }
            catch (Exception ex)
            {                
                return "";
            }
        }
    }
}
