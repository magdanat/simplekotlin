// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn(a: Any) = when(a) {
    "Hello" -> "world"
    is String -> "Say what?"
    0 -> "zero"
    1 -> "one"
    in 2..10 -> "low number"
    is Int -> "a number"
    else -> "I don't understand"
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int,b: Int, func: (Int, Int) -> Int): Int {
    val num = func(a, b)
    return num
}

// write a class "Person" with first name, last name and age

class Person(firstName: String, lastName: String, age:Int) {
    var firstName = firstName
    var lastName = lastName
    var age = age

    fun equals(o: Person): Boolean {
        if (o == this) {
            return true
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        var result:Int = hashCode()
        if (result == 0) {
            result = 6
            result = 31 * result + firstName.hashCode()
            result = 31 * result + lastName.hashCode()
            result = 31 * result + age
        }
        return result
    }

    private var _debugString: String? = null
    public val debugString: String
        get() {
            if (_debugString == null) {
                _debugString = "[Person firstName:$firstName lastName:$lastName age:$age]"
            }
            return _debugString ?: throw AssertionError("Set to null by another thread")
        }

}

// write a class "Money"
class Money(var amount: Int, var currency: String ="USD") {
    init {
        if (currency != "USD" && currency != "GBP" && currency != "EUR" && currency != "CAN") {
            throw Exception("Currency is not valid")
        }
    }

    public fun convert(a: String): Money {
        if (a == "USD") {
            if (this.currency == "GBP") {
                return Money(this.amount * 2, "USD")
            } else if (this.currency == "EUR") {
                return Money(this.amount * 2 / 3, "USD")
            } else if (this.currency == "CAN") {
                return Money(this.amount * 4 / 5, "USD")
            } else {
                return Money(this.amount, "USD")
            }
        } else if (a == "GBP") {
            if (this.currency == "USD") {
                return Money(this.amount / 2, "GBP")
            } else if (this.currency == "EUR") {
                return Money(this.amount / 3, "GBP")
            } else if (this.currency == "CAN") {
                return Money(this.amount * 15 / 6, "GBP")
            } else {
                return Money(this.amount, "GBP")
            }
        } else if (a == "EUR") {
            if (this.currency == "USD") {
                return Money(this.amount * 3 / 2, "EUR")
            } else if (this.currency == "CAN") {
                return Money(this.amount * 6 / 5, "EUR") 
            } else if (this.currency == "GBP") {
                return Money(this.amount * 3, "EUR")
            } else {
                return Money(this.amount, "EUR")
            }
        } else {
            if (this.currency =="USD") {
                return Money(this.amount * 5 / 4, "CAN")
            } else if (this.currency == "EUR") {
                return Money(this.amount * 5 / 6, "CAN") 
            } else if (this.currency == "GBP") {
                return Money(this.amount * 6 / 15, "CAN")
            } else {
                return Money(this.amount, "CAN")
            }
        }
    }

    operator fun plus(other: Money): Money {
        val convertedMoney = other.convert(this.currency).amount
        val newMoney = this.amount + convertedMoney
        return Money(newMoney, this.currency)
    }
}

// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
