package com.company

import java.util.*

internal object TSP {
    var two_dim_cost_matric = Array(1000) { IntArray(1000) }
    var visited_copy_array = BooleanArray(1000) //odwiedzony
    var no_of_vertices = 0
    var current_vertex = 0
    var total_min_cost = 0 //vertex= wierzchołek
    var FirstMiasto = ""
    var LastMiast = ""
    var max = 999
    var min = 1
    var noweJ = 0
    var noweI = 0
    var ILLE = 0
    fun zamienNazwa(i: Int, j: Int) {
        when (i) {
            0 -> FirstMiasto = "Gdynia"
            1 -> FirstMiasto = "Gdansk"
            2 -> FirstMiasto = "Krakow"
            3 -> FirstMiasto = "Warszawa"
            4 -> FirstMiasto = "Lodz"
            5 -> FirstMiasto = "Poznan"
            6 -> FirstMiasto = "SuchyDwor"
            7 -> FirstMiasto = "Koleczkowo"
            8 -> FirstMiasto = "Pierwoszyno"
            9 -> FirstMiasto = "Kosakowo"
            10 -> FirstMiasto = "Puck"
            11 -> FirstMiasto = "Ruda"
            12 -> FirstMiasto = "MałoCyce"
            13 -> FirstMiasto = "Chojnice"
            14 -> FirstMiasto = "Adowo"
            15 -> FirstMiasto = "Stulejowo"
        }
        when (j) {
            0 -> LastMiast = "Gdynia"
            1 -> LastMiast = "Gdansk"
            2 -> LastMiast = "Krakow"
            3 -> LastMiast = "Warszawa"
            4 -> LastMiast = "Lodz"
            5 -> LastMiast = "Poznan"
            6 -> LastMiast = "SuchyDwor"
            7 -> LastMiast = "Koleczkowo"
            8 -> LastMiast = "Pierwoszyno"
            9 -> LastMiast = "Kosakowo"
            10 -> LastMiast = "Puck"
            11 -> LastMiast = "Ruda"
            12 -> LastMiast = "MałoCyce"
            13 -> LastMiast = "Chojnice"
            14 -> LastMiast = "Adowo"
            15 -> LastMiast = "Stulejowo"
        }
    } // zamiana int i && j na string

    fun zamienSciezke(obecny: Int, noweJ: Int, ILLE: Int) {
        when (obecny) {
            1 -> FirstMiasto = "Gdynia"
            2 -> FirstMiasto = "Gdansk"
            3 -> FirstMiasto = "Krakow"
            4 -> FirstMiasto = "Warszawa"
            5 -> FirstMiasto = "Lodz"
            6 -> FirstMiasto = "Poznan"
            7 -> FirstMiasto = "SuchyDwor"
            8 -> FirstMiasto = "Koleczkowo"
            9 -> FirstMiasto = "Pierwoszyno"
            10 -> FirstMiasto = "Kosakowo"
            11 -> FirstMiasto = "Puck"
            12 -> FirstMiasto = "Ruda"
            13 -> FirstMiasto = "MałoCyce"
            14 -> FirstMiasto = "Chojnice"
            15 -> FirstMiasto = "Adowo"
            16 -> FirstMiasto = "Stulejowo"
        }
    }

    fun inputs() {
        val scobj = Scanner(System.`in`)
        print("Podaj ilosc miast: ") //ilosc miast
        no_of_vertices = scobj.nextInt()
        print("Podaj poczatkowy wierzcholek: ")
        current_vertex = scobj.nextInt()
        for (i in 0 until no_of_vertices) {
            for (j in 0 until no_of_vertices) {
                if (i == j) // wynik tych samych miast
                {
                    two_dim_cost_matric[i][j] = 0
                } else {
                    zamienNazwa(i, j)

                    //System.out.print(("Podaj kosz miedzy miastami: "+(FirstMiasto)+" --> "+(LastMiast)+": "));
                    two_dim_cost_matric[i][j] = Math.floor(Math.random() * (max - min + 1) + min).toInt()
                }
            }
        }
        print("\n")
        print("\n")
        for (i in 0 until no_of_vertices) {
            for (j in 0 until no_of_vertices) {
                print(two_dim_cost_matric[i][j].toString() + "    ")
            }
            println()
        }
    }

    fun next_visit(c_vertex: Int): Int {
        var MIN = Int.MAX_VALUE
        var cost_spent = 0
        var next_vertex = Int.MAX_VALUE
        for (i in 0 until no_of_vertices) {
            if (two_dim_cost_matric[c_vertex - 1][i] != 0 && !visited_copy_array[i]) {
                if (two_dim_cost_matric[c_vertex][i] + two_dim_cost_matric[i][c_vertex] < MIN) {
                    MIN = two_dim_cost_matric[c_vertex - 1][i] + two_dim_cost_matric[i][c_vertex - 1]
                    cost_spent = two_dim_cost_matric[c_vertex - 1][i]
                    next_vertex = i
                }
            }
        }
        if (MIN != Int.MAX_VALUE) {
            total_min_cost += cost_spent
        }
        return next_vertex + 1
    }

    fun shortest_distance(c_vertex: Int) {
        zamienSciezke(c_vertex, noweJ, ILLE)
        visited_copy_array[c_vertex - 1] = true
        print(FirstMiasto + "--->")
        val nxt_visit = next_visit(c_vertex)
        if (nxt_visit == Int.MAX_VALUE + 1) {
            print(current_vertex)
            total_min_cost += two_dim_cost_matric[c_vertex - 1][current_vertex - 1]
            return
        }
        shortest_distance(nxt_visit)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val scobj = Scanner(System.`in`)
        inputs()
        print("\nSciezka: ")
        shortest_distance(current_vertex)
        println()
        println(
            """
                
                Koszt podruzy:$total_min_cost
                """.trimIndent()
        )
        println("\n")
        print("Podaj pierwszy wierzcholek: ")
        noweI = scobj.nextInt()
        print("Podaj drugie miasto: ")
        noweI = scobj.nextInt()
        print("Nowa wartosc dla miasta1")
        ILLE = scobj.nextInt()
        for (i in 0 until no_of_vertices) {
            for (j in 0 until no_of_vertices) {
                if (noweJ == i && noweJ == j) {
                    two_dim_cost_matric[noweI][noweJ] = ILLE
                }
                print(two_dim_cost_matric[i][j].toString() + "    ")
            }
            println()
        }
    }
}