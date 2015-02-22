#!/bin/sh
# file convert.sh

for i in {1..3769}
do
  obabel "new$i.mol" -O "out$i.png"
  echo $i
done
