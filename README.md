# SquidGameRiddler
Solution to Riddler Classic @ https://fivethirtyeight.com/features/can-you-survive-squid-game-riddler/ inspired by game nr. 5 (the glass bridge crossing) of Netflix's awesome Korean series [Squid Game](https://www.netflix.com/title/81040344).

In this high-tension game, 16 players must cross, in order, a 18-step glass bridge, guessing at each step which of two glass squares will support their weight without breaking.

Below are the survival probabilities of each player depending on their order number and also the total number of expected survivors, assuming no time limit and no player resorting to tricks. All numbers were calculated as exact fractions with a [short program in Scala 2.12.11 using cached recursion](https://github.com/stefperf/SquidGameRiddler/blob/main/SquidGameRiddler.scala) on the function e(P, S) := `expected number of survivors given that P players and S steps are left`, exploiting this relationship:

e(P, S)   =   P / 2^S + Sum e(P-1, s) / 2^(S-s) for s in 0...S-1

(i.e.: there are P survivors if the 1st player guesses all correct steps, otherwise there are the survivors of a game with P-1 players and with the remaining number of unknown steps)

Then the survival probability of each p-th player can be calculated simply as e(p, S) - e(p-1, S), exploiting the relationship:

e(P, S)   =   Sum Prob\[ p-th player survives \] for p in 1...P

It turns out that the 16th player is 261'972 times likelier to survive than the 1st player.

The data has also been tabulated and visualized in [this Google spreadsheet](https://docs.google.com/spreadsheets/d/1DdTaY0syGlr8gimWOWjR5Hk_FeOtbFyqBC-CHCyZfo4/edit?usp=sharing).

# Detailed results

Playing Squid Game's glass bridge crossing with 16 players and 18 steps:

Player nr. 1 survives with probability 1/262144 ~= 0.0000038147.

Player nr. 2 survives with probability 19/262144 ~= 0.0000724792.

Player nr. 3 survives with probability 43/65536 ~= 0.0006561279.

Player nr. 4 survives with probability 247/65536 ~= 0.0037689209.

Player nr. 5 survives with probability 253/16384 ~= 0.0154418945.

Player nr. 6 survives with probability 1577/32768 ~= 0.0481262207.

Player nr. 7 survives with probability 7795/65536 ~= 0.1189422607.

Player nr. 8 survives with probability 15751/65536 ~= 0.2403411865.

Player nr. 9 survives with probability 53381/131072 ~= 0.4072647095.

Player nr. 10 survives with probability 77691/131072 ~= 0.5927352905.

Player nr. 11 survives with probability 49785/65536 ~= 0.7596588135.

Player nr. 12 survives with probability 57741/65536 ~= 0.8810577393.

Player nr. 13 survives with probability 31191/32768 ~= 0.9518737793.

Player nr. 14 survives with probability 16131/16384 ~= 0.9845581055.

Player nr. 15 survives with probability 65289/65536 ~= 0.9962310791.

Player nr. 16 survives with probability 65493/65536 ~= 0.9993438721.

The total expected number of survivors is 458757/65536 ~= 7.0000762939.
