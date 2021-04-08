import java.awt.Color

fun interface Function {
    operator fun invoke(i: Double): Double
    operator fun invoke(i: Number): Double = invoke(i.toDouble())
}

val t = Function { it }

infix operator fun Function.div(d: Number) = Function { invoke(it) / d.toDouble() }
infix operator fun Number.div(f: Function) = Function { f(it) / toDouble() }

infix operator fun Function.times(d: Number) = Function { invoke(it) * d.toDouble() }
infix operator fun Number.times(f: Function) = Function { f(it) * toDouble() }

infix operator fun Function.plus(d: Number) = Function { invoke(it) + d.toDouble() }
infix operator fun Number.plus(f: Function) = Function { f(it) + toDouble() }

infix operator fun Function.minus(d: Number) = Function { invoke(it) - d.toDouble() }
infix operator fun Number.minus(f: Function) = Function { f(it) - toDouble() }

infix operator fun Function.times(f: Function) = Function { invoke(it) * f(it) }

operator fun Function.unaryMinus() = Function { -invoke(it) }
operator fun Function.unaryPlus() = Function { invoke(it) }

class Curve {
    lateinit var range: IntRange
    lateinit var x: Function
    lateinit var y: Function
}

class Curves {
    private val curves: MutableList<Curve> = mutableListOf()

    fun curve(construct: Curve.() -> Unit) {
        curves.add(Curve().apply(construct))
    }

    fun createPlot(scale: Double, quality: Int, color: Color = Color.WHITE)
            = CurvesPlot(curves, scale, quality, color)
}

fun curves(construct: Curves.() -> Unit): Curves {
    return Curves().apply(construct)
}
