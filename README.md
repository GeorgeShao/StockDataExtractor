# StockDataExtractor
Java application that extracts historical stock data from Yahoo Finance.

**Development State:**
Finished, may / may not receive future updates.

## Installation Instructions
Simply download this file here from GitHub and open it in Eclipse or another IDE of your choice. After that, run the "Extractor.java" file and the project will get historical stock data from Yahoo Finance. Stock data will be shown in the output and it will also be stored in an array of the object "Day".

## How It Works (No API)
This project does not use any type of API. It establishes an connection to Yahoo Finance and it takes the HTML from the historical data stock website. It then trims the HTML in order to get the historical data in JSON form. After that, all the historical data is stored in an object array.

The above explanation of the code might sound complicated, but take a look at the code yourself and you'll see it's actually not too complex.

## Possible Uses
Someone can copy and paste only the Extractor.java and Day.java files and modify them to suit their own use case. For example, you might use this data to display the stock prices on a chart or add a GUI to the program to make it more user-friendly.

## Why StockDataExtractor?
1. Licensed under MIT License. (very permissive with very little restrictions) See LICENSE.txt for more details.
2. Free to use and modify to fit your use case. See LICENSE.txt for more details.
3. No data request limits. (Unless Yahoo decides to limit the amount of times you can visit their website)
