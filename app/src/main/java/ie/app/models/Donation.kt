package ie.app.models

class Donation {
    @JvmField
    var id = 0
    @JvmField
    var amount: Int
    @JvmField
    var method: String

    constructor(amount: Int, method: String) {
        this.amount = amount
        this.method = method
    }

    constructor() {
        amount = 0
        method = ""
    }

    override fun toString(): String {
        return "$id, $amount, $method"
    }
}