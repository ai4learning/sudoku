#!/bin/sh
if [[ -n $1 && -d $1 ]];then
  rm -Rf $1"dist"
  cp -R content dist static index.html $1
else
  echo "error: need a [dir] param"
fi