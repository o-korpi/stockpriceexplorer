package sharepriceexplorer.main

import kweb.*
import kweb.plugins.fomanticUI.*
import kweb.InputType.*
import kweb.state.*

import sharepriceexplorer.main.data.*


fun main() {
    Kweb(port = 16097, plugins = listOf(fomanticUIPlugin)) {
        doc.body {

            route {
                path("/") {
                    url.value = "/stock/AAPL"
                }

                path("/stock/{ticker}") { params ->
                    val ticker: KVar<String> = params.getValue("ticker")
                    val price: KVal<String> = ticker.map { getFormattedStockPrice(it) }

                    // spacing, idk how padding or margins works with fomantic
                    for (i in 1..9) br()

                    div(fomantic.ui.centered.raised.card).new {

                        br()
                        h2(fomantic.ui.text.center).text("Search for a stock")

                        div(fomantic.ui.icon.input).new {
                            val searchInput = input(type = text, placeholder = "Search")

                            searchInput.on.keypress {
                                if (it.key == "Enter") {
                                    ticker.value = searchInput.value.value
                                }
                            }
                            i(fomantic.search.icon)
                        }

                        br()
                        br()
                        div(fomantic.ui.statistic).new {
                            val stockPriceLabel = div(fomantic.label)
                            val stockPriceDisplay = div(fomantic.value)

                            stockPriceLabel.text(ticker.map { "$it (15-min delay)" })
                            stockPriceDisplay.text(price)
                        }
                        br()

                    }

                }

            }
        }
    }
}
