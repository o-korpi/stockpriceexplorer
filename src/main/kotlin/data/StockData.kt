package sharepriceexplorer.main.data

import java.math.BigDecimal
import yahoofinance.YahooFinance


fun getStockPrice(ticker: String): BigDecimal? {
    // Gets the current price (15-min delay) for a given ticker, returns -1 if the ticker is invalid

    if (ticker.isEmpty()) return null
    if (ticker.length > 10) return null

    val price: BigDecimal = try {
        YahooFinance.get(ticker).quote.price
    } catch(e: NullPointerException) {
        return null
    }

    return price
}


fun getFormattedStockPrice(ticker: String): String {
    // Gets the current price (15-min delay) for a given ticker
    val price = getStockPrice(ticker)

    if (price is BigDecimal) return "$price"
    return "Invalid ticker"
}
