import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin

@DslMarker
annotation class CurvesDSL

fun interface Function {
    operator fun invoke(i: Number): Double
}

fun sin(f: Function) = Function { sin(f(it)) }
fun cos(f: Function) = Function { cos(f(it)) }

infix operator fun Function.div(d: Number) = Function { invoke(it) / d.toDouble() }
infix operator fun Function.times(d: Number) = Function { invoke(it) * d.toDouble() }
infix operator fun Function.plus(d: Number) = Function { invoke(it) + d.toDouble() }
infix operator fun Function.minus(d: Number) = Function { invoke(it) - d.toDouble() }

infix operator fun Number.div(f: Function) = Function { f(it) / toDouble() }
infix operator fun Number.times(f: Function) = Function { f(it) * toDouble() }
infix operator fun Number.plus(f: Function) = Function { f(it) + toDouble() }
infix operator fun Number.minus(f: Function) = Function { f(it) - toDouble() }

infix operator fun Function.plus(f: Function) = Function { invoke(it) + f(it) }
infix operator fun Function.minus(f: Function) = Function { invoke(it) - f(it) }
infix operator fun Function.times(f: Function) = Function { invoke(it) * f(it) }
infix operator fun Function.div(f: Function) = Function { invoke(it) / f(it) }

operator fun Function.unaryMinus() = Function { -invoke(it) }
operator fun Function.unaryPlus() = Function { invoke(it) }

@CurvesDSL
class Curve {
    val t = Function { it.toDouble() }

    lateinit var range: Iterable<Int>
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
