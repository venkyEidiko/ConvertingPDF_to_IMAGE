package com.CCMS.PdfToJpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

@SpringBootApplication
public class PdfToJpgApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfToJpgApplication.class, args);

		try {
			String sourceDir = "C:\\Users\\ramu\\Desktop\\PDF\\ilovepdf_merged.pdf"; // Pdf files are read from
																						// this folder
			String destinationDir = "C:\\Users\\ramu\\Desktop\\PDF\\hey\\Data"; // converted images from pdf document
			// are saved here

			File sourceFile = new File(sourceDir);
			File destinationFile = new File(destinationDir);
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
			}
			if (sourceFile.exists()) {
				System.out.println("Images copied to Folder: " + destinationFile.getName());
				PDDocument document = PDDocument.load(sourceDir);
				List<PDPage> list = document.getDocumentCatalog().getAllPages();
				System.out.println("Total files to be converted -> " + list.size());

				String fileName = sourceFile.getName().replace(".pdf", "");
				System.out.println("fileName::" + fileName);
				int pageNumber = 1;
				for (PDPage page : list) {
					BufferedImage image = page.convertToImage();
					if (pageNumber <= 6) {

						File outputfile = new File(destinationDir + fileName + "_" + pageNumber + ".png");
//					System.out.println("Image Created -> " + outputfile.getName());
//					ImageIO.write(image, "png", outputfile);
//					ImageIO.write(image , "jpg", new File( destinationDir +fileName+"_"+pageNumber+".jpg" ));
						ImageIO.write(image, "jpeg", new File(destinationDir + fileName + "_" + pageNumber + ".jpeg"));
//					ImageIO.write(image , "png", new File( destinationDir +fileName+"_"+pageNumber+".png" ));
//					ImageIO.write(image , "bmp", new File( destinationDir +fileName+"_"+pageNumber+".bmp" ));
//					ImageIO.write(image , "gif", new File( destinationDir +fileName+"_"+pageNumber+".gif" ));
						pageNumber++;
					}
				}
				document.close();
				System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
			} else {
				System.err.println(sourceFile.getName() + " File not exists");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
