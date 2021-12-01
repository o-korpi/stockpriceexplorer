package sharepriceexplorer.main.data

import java.math.BigDecimal
import yahoofinance.YahooFinance


fun getStockPrice(ticker: String): BigDecimal {
    // Gets the current price (15-min delay) for a given ticker, returns -1 if the ticker is invalid

    val invalidReturn = BigDecimal(-1)

    if (ticker.isEmpty()) return invalidReturn
    if (ticker.length > 10) return invalidReturn

    val price: BigDecimal = try {
        YahooFinance.get(ticker).quote.price
    } catch(e: NullPointerException) {
        invalidReturn
    }

    return price
}


private fun formatStockPriceData(price: BigDecimal): String {
    // Turn invalid prices to error messages
    if (price != BigDecimal(-1)) return "$price"
    return "Invalid ticker"
}


fun getFormattedStockPrice(ticker: String): String {
    // Gets the current price (15-min delay) for a given ticker

    return formatStockPriceData(getStockPrice(ticker))
}
