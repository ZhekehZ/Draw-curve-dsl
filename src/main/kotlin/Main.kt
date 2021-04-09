import kotlin.math.sqrt

fun main() {
    curves {
        curve {
            range = 0..10
            x = cos(3 * t) + 2.5 * cos(2 * t)
            y = sin(3 * t) - 2.5 * sin(2 * t)
        }
        curve {
            range = 0..10
            x = sqrt(2.0) / 2 * (cos(3 * t) + sin(3 * t) +
                            2.5 * (cos(2 * t) - sin(2 * t)))
            y = sqrt(2.0) / 2 * (cos(3 * t) - sin(3 * t) +
                            2.5 * (cos(2 * t) + sin(2 * t)))
        }
        curve {
            range = 0..7
            x = sin(t)
            y = cos(t)
        }
    }.createPlot(0.1, 10)
     .draw(800, 600)
}