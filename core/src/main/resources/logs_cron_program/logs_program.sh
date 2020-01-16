#!/bin/bash
directories=()
files=/var/log/tomcat8/*/
for file in $files
do
f="$(echo $file | cut -d'/' -f5)"
d="$(echo $f | cut -d'-' -f1)-$(echo $f | cut -d'-' -f2)"
mkdir $d
directories+=("$d")
cp -r $file $d/
done
unique_directories=($(printf "%s\n" "${directories[@]}" | sort -u | tr '\n' ' '))
for aws_folder in ${unique_directories[@]}
do
aws s3 cp $aws_folder/ s3://nfapi-uat1-logs/$aws_folder --recursive
rm -r $aws_folder
done
for file in $files
do
rm -r $file
done

