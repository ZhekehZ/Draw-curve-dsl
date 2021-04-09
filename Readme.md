### Draw-curve-dsl

#### DSL Exaples

##### Config
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

##### Output
<p align="center"> 
<img src="https://user-images.githubusercontent.com/14273096/114096720-c8d13600-98c7-11eb-865e-74362412b00a.png" width="700"/>
</p>

##### Config
```kotlin
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
```

##### Output
<p align="center"> 
<img src="https://user-images.githubusercontent.com/14273096/114156021-cbb14280-992a-11eb-97f1-d5cc60e932b3.png" width="700"/>
</p>
