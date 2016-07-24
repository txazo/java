#!/bin/bash

function executeCommand() {
    case $1 in
      version)
            java -version
            ;;
      showversion)
            java -showversion org.txazo.java.jvm.args.JVMXXArgs
            ;;
      client)
            java -client
            ;;
      server)
            java -server
            ;;
      *)
            usage
            ;;
    esac
}

function usage() {
    echo "Usage: jvm.sh args"
}

function main() {
    executeCommand $1
}

main $1