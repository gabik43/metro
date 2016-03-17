package com.gabik.metro.grahp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GaBiK on 13.03.2016.
 */
public class Graph {
    private static Logger log = Logger.getLogger(Graph.class.getName());

    private int countStations;
    public Graph(List<TimeBetweenTwoElements> timeBetweenTwoElementsesList, int countStations){
        this.countStations = countStations;
        initMatrix(timeBetweenTwoElementsesList);
    }
    private boolean isSetMatrix;
    // Матрица смежности
    private float[][] matrixTimeBetweenNodeBegEndStations;

    private float minDistance = 0;


    private List<Integer> stationsIndexPath = new ArrayList<Integer>();

    public void initMatrix(List<TimeBetweenTwoElements> timeBetweenTwoElementsesList)  {
        matrixTimeBetweenNodeBegEndStations = new float[countStations][countStations];
        for (TimeBetweenTwoElements timeBetweenTwoElement : timeBetweenTwoElementsesList){
            int indexOne = timeBetweenTwoElement.getIndexStationOne();
            int indexTwo = timeBetweenTwoElement.getIndexStationTwo();
            int time = timeBetweenTwoElement.getTime();
            matrixTimeBetweenNodeBegEndStations[indexOne][indexTwo] = time;
            matrixTimeBetweenNodeBegEndStations[indexTwo][indexOne] = time;
        }
    }


    /*Расчет кратчайшего маршрута. Алгоритм Дейкстры*/
    public List<Integer> calculate(int indexBeginStation, int indexEndStation) {
        try {
            int startNode = indexBeginStation;
            int finishNode = indexEndStation;

            float distance[] = new float[countStations];
            int path[] = new int[countStations];
            int count, index = 0, i, u, m = startNode + 1;

            boolean visited[] = new boolean[countStations];
            for (i = 0; i < countStations; i++) {
                distance[i] = 50000;
                visited[i] = false;
                path[i] = -1;
            }
            distance[startNode] = 0;
            for (count = 0; count < countStations - 1; count++) {
                float min = 50000;
                for (i = 0; i < countStations; i++)
                    if (!visited[i] && distance[i] <= min) {
                        min = distance[i];
                        index = i;
                    }
                u = index;
                visited[u] = true;
                for (i = 0; i < countStations; i++)
                    if (!visited[i] && matrixTimeBetweenNodeBegEndStations[u][i] != 0 && distance[u] != 50000 && distance[u] + matrixTimeBetweenNodeBegEndStations[u][i] < distance[i]) {
                        distance[i] = (distance[u] + matrixTimeBetweenNodeBegEndStations[u][i]);
                        path[i] = u;
                    }
            }
            stationsIndexPath.clear();
            int currentIndex = finishNode;
            stationsIndexPath.add(finishNode);
            while (true) {
                stationsIndexPath.add(path[currentIndex]);

                currentIndex = path[currentIndex];
                if (currentIndex == startNode) break;
            }
            minDistance = distance[finishNode];
            return stationsIndexPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stationsIndexPath;
    }

    /*Получение дистанции текущего маршрута*/
    public float getDistance() {
        return minDistance;
    }


/*    private void printMatrix() {
        for (int i = 0; i < matrixTimeBetweenNodeBegEndStations.length - 2; i++)
            for (int j = 0; j < matrixTimeBetweenNodeBegEndStations.length - 2; j++) {
                log.info("[" + i + "] [" + j + "] " + ManagerStation.getNameNodeBegEndStation(i) + " - " + ManagerStation.getNameNodeBegEndStation(j) + ": " + matrixTimeBetweenNodeBegEndStations[i][j]);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }*/
}
