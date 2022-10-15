
# Concept

The key concept of the strip here is a 3d matrix 3x6x9.
Slice - is 2d matrix 3x6.

       _ _ _ _ _ _ _ _ _
      / / / / / / / / / /|
     / / / / / / / / / / |
    / / / / / / / / / /  |
    | | | | | | | | | |  |
    | | | | | | | | | |  |
    | | | | | | | | | |  |
    | | | | | | | | | |  /
    | | | | | | | | | | /
    |_|_|_|_|_|_|_|_|_|/

    ^
    slices


Filling of the stripe goes slice by slice.
In each iteration algorithm count the matrix of filled cells
of the slice.

        Slice               Counter
     ___________        ____________
    | 1 |   |   |       | 1 | 0 | 0 |
    |   | 2 | 3 |       | 0 | 1 | 1 |
    | 4 |   |   |       | 1 | 0 | 0 |
    |   | 6 |   |  ---> | 0 | 1 | 0 |
    |   |   | 7 |       | 0 | 0 | 1 |
    | 8 | 9 |   |       | 1 | 1 | 0 |


And mask the cells which has MAX number of filled cells
to prevent overflow.

# How to build

    $ ./gradlew build

# How to run

    $ ./gradlew run

OR

    $ java -jar build/libs/bingo90.jar 10

The last argument (optional) is the nuber of stripes to generate

# Bugs

Current implementation is far from optimal: it need to be refactored
and optimized a lot.

Also there is a stahastical failures when the last slice filling
because stahastically one of the counter row can be [5, 5, 5].
This could be fixed by counting the number of holes to.
