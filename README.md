# SquidGameRiddler
Solution to Riddler Classic @ https://fivethirtyeight.com/features/can-you-survive-squid-game-riddler/ inspired by game nr. 5 (the glass bridge crossing) of Netflix's awesome Korean series [Squid Game](https://www.netflix.com/title/81040344).

In this high-tension game, 16 players must cross, in order, a 18-step glass bridge, guessing at each step which of two glass squares will support their weight without breaking.

Below are the survival probabilities of each player depending on their order number and also the total number of expected survivors, assuming no time limit and no player resorting to tricks. All numbers were calculated as exact fractions with a [short program in Scala 2.12.11 using cached recursion](https://github.com/stefperf/SquidGameRiddler/blob/main/SquidGameRiddler.scala) on the function e(P, S) := `expected number of survivors given that P players and S steps are left`, exploiting this relationship:

e(P, S)   =   e(1, S) + Sum e(P-1, s) / 2^(S-s) for s in 0...S-1   =   1/2^S + Sum e(P-1, s) / 2^(S-s) for s in 0...S-1

Even the last player, who is more than 100'000 times likelier to survive than the unlucky first player, still has worse than even chances. The survival probability of each p-th player is calculated simply as e(p, S) - e(p-1, S), exploiting the relationship:

e(P, S)   =   Sum Prob\[ p-th player survives \] for p in 1...P

# Results

Player nr. 1 survives with probability 1/262144 ~= 0.0000038147.

Player nr. 2 survives with probability 9/131072 ~= 0.0000686646.

Player nr. 3 survives with probability 77/131072 ~= 0.0005874634.

Player nr. 4 survives with probability 417/131072 ~= 0.0031814575.

Player nr. 5 survives with probability 1607/131072 ~= 0.0122604370.

Player nr. 6 survives with probability 4701/131072 ~= 0.0358657837.

Player nr. 7 survives with probability 10889/131072 ~= 0.0830764771.

Player nr. 8 survives with probability 20613/131072 ~= 0.1572647095.

Player nr. 9 survives with probability 1/4 ~= 0.2500000000.

Player nr. 10 survives with probability 44923/131072 ~= 0.3427352905.

Player nr. 11 survives with probability 54647/131072 ~= 0.4169235229.

Player nr. 12 survives with probability 60835/131072 ~= 0.4641342163.

Player nr. 13 survives with probability 63929/131072 ~= 0.4877395630.

Player nr. 14 survives with probability 65119/131072 ~= 0.4968185425.

Player nr. 15 survives with probability 65459/131072 ~= 0.4994125366.

Player nr. 16 survives with probability 65527/131072 ~= 0.4999313354.

The total expected number of survivors is 983041/262144 ~= 3.7500038147.
