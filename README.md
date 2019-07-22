# Safeguards
2019 Fall MSSM, Summer preparation, Data Structure Assignment
This program is to find a least impacting lifeguard, by firing whom, the left coverage time of the pool is at its maximum.
Each of the inputFiles provides lines of the starting and ending point of a lifeguard's shift including the first line as the number of total guards before firing.
The outputFiles are requested to give the maximum amount of time that can still be covered if the pool owner fires 1 lifeguard.
In this project, the Class SafeGuards is created to handle all the logic and computation. First quickSort all the shifts by the values of start points. Then, caculate the total coverage time and the number of "single covered time points" of each guard. (By "single covered time points"", I mean time points, which are covered by only one person.) At last, caculate the final result by minus the minimum number of single coverage time points of one guard from Total coverage time.
The other file "GuardingTime.java" has the main function to handle all the inputFiles and output the respective output Files.
