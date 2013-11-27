#!/bin/bash

#-logfileNames init/binPackLog,default/binPackLog,gen1000/binPackLog,pop1000/binPackLog,500-500/binPackLog \
#-legendNames "initial","current","1000 generations","1000 population","500 500"

source ../createGraphs.sh \
  -path logs \
  -logfileNames init/binPackLog,stupid/binPackLog,clever/binPackLog \
  -title "binPacking" \
  -legendNames "initial","stupid","clever mutation"
