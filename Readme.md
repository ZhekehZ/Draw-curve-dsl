### Draw-curve-dsl

#### DSL Exaple
```kotlin
curves {
    curve {
        range = 0..7
        x = t * 0
        y = t
    }
    curve {
        range = -1..1
        x = 3 * (1 - t * t)
        y = 4 + (t + 1) * 3 / 2
    }
    curve {
        range = -2..2
        x = 4 - t * t
        y = t + 2
    }
}.createPlot(0.1, 10)
 .draw(800, 600)
```