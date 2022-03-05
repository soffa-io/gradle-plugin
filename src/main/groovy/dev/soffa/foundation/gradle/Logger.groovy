package dev.soffa.foundation.gradle

class Logger {

    static void error(String text) {
        println("${Ansi.RED}ERROR${Ansi.NORMAL} $text")
    }

    static void success(String text) {
        println("${Ansi.GREEN}SUCCESS${Ansi.NORMAL} $text")
    }

}
