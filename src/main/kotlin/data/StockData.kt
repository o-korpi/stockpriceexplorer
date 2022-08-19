package sharepriceexplorer.main.data

import arrow.core.Either
import java.math.BigDecimal
import yahoofinance.YahooFinance
import java.net.UnknownHostException


sealed class QueryError(val cause: String) {
    data class InvalidTicker(val msg: String) : QueryError(msg)
    data class NoConnection(val msg: String) : QueryError(msg)
}


fun getStockPrice(ticker: String): Either<QueryError, BigDecimal> {
    if (ticker.isEmpty()) return Either.Left(QueryError.InvalidTicker("Ticker must not be empty"))
    if (ticker.length > 10) return Either.Left(QueryError.InvalidTicker("Invalid ticker"))

    return try {
        when (val price = YahooFinance.get(ticker)?.quote?.price) {
            null -> Either.Left(QueryError.InvalidTicker("Ticker not found"))
            else -> Either.Right(price)
        }
    } catch (_: UnknownHostException) {
        Either.Left(QueryError.NoConnection("Cannot connect to Yahoo Finance API"))
    }
}
