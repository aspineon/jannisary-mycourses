package entities.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelPOI 
{
	HSSFWorkbook scheduleWorkbook = new HSSFWorkbook();
	
	//******************************************************************************
	// seasonYearOne seasonYearTwo'dan kasit 2010-2011 gibi bir sezonun belirtilmesi
	// seasonType ise Fall yada Spring gibi tipinin belitilmesidir...
	
	public void writeToExcelPOI(int fallYear, int springYear, String seasonType)
	{
		try
		{
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
									
			File scheduleFolder = new File("C:\\Schedule Files\\");
			
			// siniflara ait excel dosyasinin yaratilmasi ***********************
			File scheduleFile = new File("C:\\Schedule Files\\"+fallYear+"_"+springYear+"_"+seasonType+".xls");
				
			if(!scheduleFolder.exists())
			{
				scheduleFolder.mkdir();
			}
			if(!scheduleFile.exists())
			{
				scheduleFile.createNewFile();
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(scheduleFile);
			scheduleWorkbook.write(fileOutputStream);
			
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
	
	public void generateFreshmanSheet(String freshmanTable[][])
	{
		try
		{
			HSSFSheet freshmanSheet = scheduleWorkbook.createSheet("Freshman");
			HSSFCellStyle freshmanStyle = scheduleWorkbook.createCellStyle();
												
			//*********** Border Operations *************************************
			freshmanStyle.setBorderBottom(CellStyle.BORDER_THIN);
			freshmanStyle.setBorderTop(CellStyle.BORDER_THIN);
			freshmanStyle.setBorderRight(CellStyle.BORDER_THIN);
			freshmanStyle.setBorderLeft(CellStyle.BORDER_THIN);			
			//*******************************************************************
			
			//*********** Alignment operations ***************************************
			
			freshmanStyle.setAlignment(CellStyle.ALIGN_CENTER);		
			
			freshmanStyle.setWrapText(true);		
			//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
			freshmanSheet.setColumnWidth(0, 4500);
			freshmanSheet.setColumnWidth(1, 4000);
			freshmanSheet.setColumnWidth(2, 4000);
			freshmanSheet.setColumnWidth(3, 4000);
			freshmanSheet.setColumnWidth(4, 4000);
			freshmanSheet.setColumnWidth(5, 4000);
			
			//*******************************************************************
			
			for(int row = 0; row < 10; row++)
			{
				HSSFRow freshmanRow = freshmanSheet.createRow(row);
		
				for(int column = 0; column < 6; column++)
				{
					HSSFCell freshmanCell = freshmanRow.createCell(column);
					
					freshmanCell.setCellValue(freshmanTable[row][column]);
					
					if(column >= 0 && row == 0)
					{
						freshmanStyle.setFillForegroundColor(HSSFColor.TURQUOISE.index);
						freshmanStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						//freshmanStyle.setFillBackgroundColor(HSSFColor.TURQUOISE.index);
						freshmanCell.setCellStyle(freshmanStyle);
					}
					else if(column == 0 && row >= 1)
					{
						HSSFCellStyle freshmanStyle3 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						freshmanStyle3.setBorderBottom(CellStyle.BORDER_THIN);
						freshmanStyle3.setBorderTop(CellStyle.BORDER_THIN);
						freshmanStyle3.setBorderRight(CellStyle.BORDER_THIN);
						freshmanStyle3.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						freshmanStyle3.setAlignment(CellStyle.ALIGN_CENTER);		
						
						freshmanStyle3.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						freshmanSheet.setColumnWidth(0, 4500);
						freshmanSheet.setColumnWidth(1, 4000);
						freshmanSheet.setColumnWidth(2, 4000);
						freshmanSheet.setColumnWidth(3, 4000);
						freshmanSheet.setColumnWidth(4, 4000);
						freshmanSheet.setColumnWidth(5, 4000);
						
						freshmanStyle3.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
						freshmanStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						freshmanCell.setCellStyle(freshmanStyle3);
					}
					else if((column == 2 || column == 4) && row > 0)
					{
						HSSFCellStyle freshmanStyle4 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						freshmanStyle4.setBorderBottom(CellStyle.BORDER_THIN);
						freshmanStyle4.setBorderTop(CellStyle.BORDER_THIN);
						freshmanStyle4.setBorderRight(CellStyle.BORDER_THIN);
						freshmanStyle4.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						freshmanStyle4.setAlignment(CellStyle.ALIGN_CENTER);		
						
						freshmanStyle4.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						freshmanSheet.setColumnWidth(0, 4500);
						freshmanSheet.setColumnWidth(1, 4000);
						freshmanSheet.setColumnWidth(2, 4000);
						freshmanSheet.setColumnWidth(3, 4000);
						freshmanSheet.setColumnWidth(4, 4000);
						freshmanSheet.setColumnWidth(5, 4000);
						
						freshmanStyle4.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
						freshmanStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						freshmanCell.setCellStyle(freshmanStyle4);
					}
					else
					{
						HSSFCellStyle freshmanStyle2 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						freshmanStyle2.setBorderBottom(CellStyle.BORDER_THIN);
						freshmanStyle2.setBorderTop(CellStyle.BORDER_THIN);
						freshmanStyle2.setBorderRight(CellStyle.BORDER_THIN);
						freshmanStyle2.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						freshmanStyle2.setAlignment(CellStyle.ALIGN_CENTER);		
						
						freshmanStyle2.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						freshmanSheet.setColumnWidth(0, 4500);
						freshmanSheet.setColumnWidth(1, 4000);
						freshmanSheet.setColumnWidth(2, 4000);
						freshmanSheet.setColumnWidth(3, 4000);
						freshmanSheet.setColumnWidth(4, 4000);
						freshmanSheet.setColumnWidth(5, 4000);
						freshmanCell.setCellStyle(freshmanStyle2);
					}
					
				}
			}
						
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
	
	public void generateSophomoreSheet(String sophomoreTable[][])
	{
		try
		{
			HSSFSheet sophomoreSheet = scheduleWorkbook.createSheet("Sophomore");
			HSSFCellStyle sophomoreStyle = scheduleWorkbook.createCellStyle();
			
			//*********** Border Operations *************************************
			sophomoreStyle.setBorderBottom(CellStyle.BORDER_THIN);
			sophomoreStyle.setBorderTop(CellStyle.BORDER_THIN);
			sophomoreStyle.setBorderRight(CellStyle.BORDER_THIN);
			sophomoreStyle.setBorderLeft(CellStyle.BORDER_THIN);			
			//*******************************************************************
			
			//*********** Alignment operations ***************************************
			
			sophomoreStyle.setAlignment(CellStyle.ALIGN_CENTER);		
			
			sophomoreStyle.setWrapText(true);		
			sophomoreStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
			sophomoreSheet.setColumnWidth(0, 4500);
			sophomoreSheet.setColumnWidth(1, 4000);
			sophomoreSheet.setColumnWidth(2, 4000);
			sophomoreSheet.setColumnWidth(3, 4000);
			sophomoreSheet.setColumnWidth(4, 4000);
			sophomoreSheet.setColumnWidth(5, 4000);
			
			for(int row = 0; row < 10; row++)
			{
				HSSFRow sophomoreRow = sophomoreSheet.createRow(row);
				
				for(int column = 0; column < 6; column++)
				{
					HSSFCell sophomoreCell = sophomoreRow.createCell(column);
					sophomoreCell.setCellValue(sophomoreTable[row][column]);

					if(column >= 0 && row == 0)
					{
						sophomoreStyle.setFillForegroundColor(HSSFColor.TURQUOISE.index);
						sophomoreStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						//freshmanStyle.setFillBackgroundColor(HSSFColor.TURQUOISE.index);
						sophomoreCell.setCellStyle(sophomoreStyle);
					}
					else if(column == 0 && row >= 1)
					{
						HSSFCellStyle sophomoreStyle3 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						sophomoreStyle3.setBorderBottom(CellStyle.BORDER_THIN);
						sophomoreStyle3.setBorderTop(CellStyle.BORDER_THIN);
						sophomoreStyle3.setBorderRight(CellStyle.BORDER_THIN);
						sophomoreStyle3.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						sophomoreStyle3.setAlignment(CellStyle.ALIGN_CENTER);		
						
						sophomoreStyle3.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						sophomoreSheet.setColumnWidth(0, 4500);
						sophomoreSheet.setColumnWidth(1, 4000);
						sophomoreSheet.setColumnWidth(2, 4000);
						sophomoreSheet.setColumnWidth(3, 4000);
						sophomoreSheet.setColumnWidth(4, 4000);
						sophomoreSheet.setColumnWidth(5, 4000);
						
						sophomoreStyle3.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
						sophomoreStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						sophomoreCell.setCellStyle(sophomoreStyle3);
					}
					else if((column == 2 || column == 4) && row > 0)
					{
						HSSFCellStyle sophomoreStyle4 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						sophomoreStyle4.setBorderBottom(CellStyle.BORDER_THIN);
						sophomoreStyle4.setBorderTop(CellStyle.BORDER_THIN);
						sophomoreStyle4.setBorderRight(CellStyle.BORDER_THIN);
						sophomoreStyle4.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						sophomoreStyle4.setAlignment(CellStyle.ALIGN_CENTER);		
						
						sophomoreStyle4.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						sophomoreSheet.setColumnWidth(0, 4500);
						sophomoreSheet.setColumnWidth(1, 4000);
						sophomoreSheet.setColumnWidth(2, 4000);
						sophomoreSheet.setColumnWidth(3, 4000);
						sophomoreSheet.setColumnWidth(4, 4000);
						sophomoreSheet.setColumnWidth(5, 4000);
						
						sophomoreStyle4.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
						sophomoreStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						sophomoreCell.setCellStyle(sophomoreStyle4);
					}
					else
					{
						HSSFCellStyle sophomoreStyle2 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						sophomoreStyle2.setBorderBottom(CellStyle.BORDER_THIN);
						sophomoreStyle2.setBorderTop(CellStyle.BORDER_THIN);
						sophomoreStyle2.setBorderRight(CellStyle.BORDER_THIN);
						sophomoreStyle2.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						sophomoreStyle2.setAlignment(CellStyle.ALIGN_CENTER);		
						
						sophomoreStyle2.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						sophomoreSheet.setColumnWidth(0, 4500);
						sophomoreSheet.setColumnWidth(1, 4000);
						sophomoreSheet.setColumnWidth(2, 4000);
						sophomoreSheet.setColumnWidth(3, 4000);
						sophomoreSheet.setColumnWidth(4, 4000);
						sophomoreSheet.setColumnWidth(5, 4000);
						sophomoreCell.setCellStyle(sophomoreStyle2);
					}
					
				}
			}
			
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
	
	public void generateJuniorSheet(String juniorTable[][])
	{
		try
		{
			HSSFSheet juniorSheet = scheduleWorkbook.createSheet("Junior");
			HSSFCellStyle juniorStyle = scheduleWorkbook.createCellStyle();
			
			//*********** Border Operations *************************************
			juniorStyle.setBorderBottom(CellStyle.BORDER_THIN);
			juniorStyle.setBorderTop(CellStyle.BORDER_THIN);
			juniorStyle.setBorderRight(CellStyle.BORDER_THIN);
			juniorStyle.setBorderLeft(CellStyle.BORDER_THIN);			
			//*******************************************************************
			
			//*********** Alignment operations ***************************************
			
			juniorStyle.setAlignment(CellStyle.ALIGN_CENTER);		
			
			juniorStyle.setWrapText(true);		
			juniorStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
			juniorSheet.setColumnWidth(0, 4500);
			juniorSheet.setColumnWidth(1, 4000);
			juniorSheet.setColumnWidth(2, 4000);
			juniorSheet.setColumnWidth(3, 4000);
			juniorSheet.setColumnWidth(4, 4000);
			juniorSheet.setColumnWidth(5, 4000);
			
			
			for(int row = 0; row < 10; row++)
			{
				HSSFRow juniorRow = juniorSheet.createRow(row);
				
				for(int column = 0; column < 6; column++)
				{
					HSSFCell juniorCell = juniorRow.createCell(column);
					juniorCell.setCellValue(juniorTable[row][column]);
					
					if(column >= 0 && row == 0)
					{
						juniorStyle.setFillForegroundColor(HSSFColor.TURQUOISE.index);
						juniorStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						//freshmanStyle.setFillBackgroundColor(HSSFColor.TURQUOISE.index);
						juniorCell.setCellStyle(juniorStyle);
					}
					else if(column == 0 && row >= 1)
					{
						HSSFCellStyle juniorStyle3 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						juniorStyle3.setBorderBottom(CellStyle.BORDER_THIN);
						juniorStyle3.setBorderTop(CellStyle.BORDER_THIN);
						juniorStyle3.setBorderRight(CellStyle.BORDER_THIN);
						juniorStyle3.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						juniorStyle3.setAlignment(CellStyle.ALIGN_CENTER);		
						
						juniorStyle3.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						juniorSheet.setColumnWidth(0, 4500);
						juniorSheet.setColumnWidth(1, 4000);
						juniorSheet.setColumnWidth(2, 4000);
						juniorSheet.setColumnWidth(3, 4000);
						juniorSheet.setColumnWidth(4, 4000);
						juniorSheet.setColumnWidth(5, 4000);
						
						juniorStyle3.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
						juniorStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						juniorCell.setCellStyle(juniorStyle3);
					}
					else if((column == 2 || column == 4) && row > 0)
					{
						HSSFCellStyle juniorStyle4 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						juniorStyle4.setBorderBottom(CellStyle.BORDER_THIN);
						juniorStyle4.setBorderTop(CellStyle.BORDER_THIN);
						juniorStyle4.setBorderRight(CellStyle.BORDER_THIN);
						juniorStyle4.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						juniorStyle4.setAlignment(CellStyle.ALIGN_CENTER);		
						
						juniorStyle4.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						juniorSheet.setColumnWidth(0, 4500);
						juniorSheet.setColumnWidth(1, 4000);
						juniorSheet.setColumnWidth(2, 4000);
						juniorSheet.setColumnWidth(3, 4000);
						juniorSheet.setColumnWidth(4, 4000);
						juniorSheet.setColumnWidth(5, 4000);
						
						juniorStyle4.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
						juniorStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						juniorCell.setCellStyle(juniorStyle4);
					}
					else
					{
						HSSFCellStyle juniorStyle2 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						juniorStyle2.setBorderBottom(CellStyle.BORDER_THIN);
						juniorStyle2.setBorderTop(CellStyle.BORDER_THIN);
						juniorStyle2.setBorderRight(CellStyle.BORDER_THIN);
						juniorStyle2.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						juniorStyle2.setAlignment(CellStyle.ALIGN_CENTER);		
						
						juniorStyle2.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						juniorSheet.setColumnWidth(0, 4500);
						juniorSheet.setColumnWidth(1, 4000);
						juniorSheet.setColumnWidth(2, 4000);
						juniorSheet.setColumnWidth(3, 4000);
						juniorSheet.setColumnWidth(4, 4000);
						juniorSheet.setColumnWidth(5, 4000);
						juniorCell.setCellStyle(juniorStyle2);
					}

				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
	
	public void generateSeniorSheet(String seniorTable[][])
	{
		try
		{
			HSSFSheet seniorSheet = scheduleWorkbook.createSheet("Senior");
			HSSFCellStyle seniorStyle = scheduleWorkbook.createCellStyle();
			
			//*********** Border Operations *************************************
			seniorStyle.setBorderBottom(CellStyle.BORDER_THIN);
			seniorStyle.setBorderTop(CellStyle.BORDER_THIN);
			seniorStyle.setBorderRight(CellStyle.BORDER_THIN);
			seniorStyle.setBorderLeft(CellStyle.BORDER_THIN);			
			//*******************************************************************
			
			//*********** Alignment operations ***************************************
			
			seniorStyle.setAlignment(CellStyle.ALIGN_CENTER);		
			
			seniorStyle.setWrapText(true);		
			seniorStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
			seniorSheet.setColumnWidth(0, 4500);
			seniorSheet.setColumnWidth(1, 4000);
			seniorSheet.setColumnWidth(2, 4000);
			seniorSheet.setColumnWidth(3, 4000);
			seniorSheet.setColumnWidth(4, 4000);
			seniorSheet.setColumnWidth(5, 4000);
			
			
			for(int row = 0; row < 10; row++)
			{
				HSSFRow seniorRow = seniorSheet.createRow(row);
				
				for(int column = 0; column < 6; column++)
				{
					HSSFCell seniorCell = seniorRow.createCell(column);
					seniorCell.setCellValue(seniorTable[row][column]);

					if(column >= 0 && row == 0)
					{
						seniorStyle.setFillForegroundColor(HSSFColor.TURQUOISE.index);
						seniorStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						//freshmanStyle.setFillBackgroundColor(HSSFColor.TURQUOISE.index);
						seniorCell.setCellStyle(seniorStyle);
					}
					else if(column == 0 && row >= 1)
					{
						HSSFCellStyle seniorStyle3 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						seniorStyle3.setBorderBottom(CellStyle.BORDER_THIN);
						seniorStyle3.setBorderTop(CellStyle.BORDER_THIN);
						seniorStyle3.setBorderRight(CellStyle.BORDER_THIN);
						seniorStyle3.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						seniorStyle3.setAlignment(CellStyle.ALIGN_CENTER);		
						
						seniorStyle3.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						seniorSheet.setColumnWidth(0, 4500);
						seniorSheet.setColumnWidth(1, 4000);
						seniorSheet.setColumnWidth(2, 4000);
						seniorSheet.setColumnWidth(3, 4000);
						seniorSheet.setColumnWidth(4, 4000);
						seniorSheet.setColumnWidth(5, 4000);
						
						seniorStyle3.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
						seniorStyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						seniorCell.setCellStyle(seniorStyle3);
					}
					else if((column == 2 || column == 4) && row > 0)
					{
						HSSFCellStyle seniorStyle4 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						seniorStyle4.setBorderBottom(CellStyle.BORDER_THIN);
						seniorStyle4.setBorderTop(CellStyle.BORDER_THIN);
						seniorStyle4.setBorderRight(CellStyle.BORDER_THIN);
						seniorStyle4.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						seniorStyle4.setAlignment(CellStyle.ALIGN_CENTER);		
						
						seniorStyle4.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						seniorSheet.setColumnWidth(0, 4500);
						seniorSheet.setColumnWidth(1, 4000);
						seniorSheet.setColumnWidth(2, 4000);
						seniorSheet.setColumnWidth(3, 4000);
						seniorSheet.setColumnWidth(4, 4000);
						seniorSheet.setColumnWidth(5, 4000);
						
						seniorStyle4.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
						seniorStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						
						seniorCell.setCellStyle(seniorStyle4);
					}
					else
					{
						HSSFCellStyle seniorStyle2 = scheduleWorkbook.createCellStyle();
						
						//*********** Border Operations *************************************
						seniorStyle2.setBorderBottom(CellStyle.BORDER_THIN);
						seniorStyle2.setBorderTop(CellStyle.BORDER_THIN);
						seniorStyle2.setBorderRight(CellStyle.BORDER_THIN);
						seniorStyle2.setBorderLeft(CellStyle.BORDER_THIN);			
						//*******************************************************************
						
						//*********** Alignment operations ***************************************
						
						seniorStyle2.setAlignment(CellStyle.ALIGN_CENTER);		
						
						seniorStyle2.setWrapText(true);		
						//freshmanStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
						seniorSheet.setColumnWidth(0, 4500);
						seniorSheet.setColumnWidth(1, 4000);
						seniorSheet.setColumnWidth(2, 4000);
						seniorSheet.setColumnWidth(3, 4000);
						seniorSheet.setColumnWidth(4, 4000);
						seniorSheet.setColumnWidth(5, 4000);
						seniorCell.setCellStyle(seniorStyle2);
					}
					
				}
			}
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
}