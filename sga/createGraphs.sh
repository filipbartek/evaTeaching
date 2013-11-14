#!/bin/bash

source ../createGraphs.sh \
  -logScale "" \
  -path logs \
  -logfileNames init/sgaLog,pop500/sgaLog,gen500/sgaLog,dim50/sgaLog \
  -title "popSize maxGenerations dimension" \
  -legendNames "initial (50 50 25)","population (500 50 25)","generations (50 500 25)","dimension (50 50 50)"
