/*
 * script to generate an error message file in wiki notation.
 * The content of the file can be imported in Confluence using "Insert -> Wiki Syntax" when editing a page.
 *
 * Currently, the Error Page can be found at:
 * https://confluence.xxx/wiki/display/Test/Fehlermeldungen
 */

import com.bee42.messages.exception.ErrorMessages
import java.util.logging.Logger

// using java.util.Logging here to avoid nasty message in maven console output
def logger = Logger.getLogger("Error Message Wiki Export")

def dir = new File("target")
dir.mkdirs()

def wikiFile = new File(dir, "error_messages.wiki.txt")

def errorMessage = ErrorMessages.enumConstants

wikiFile.write("||Fehlernummer||Fehlertext||Beschreibung||\n")

errorMessage.each {message ->

    def code = message.code
    def text = message.textWithPlaceholders
    def description = ""


    if (message.description) {
        description = message.description
        if (description.trim().endsWith(".")) {
            description += "\n"
        } else {
            description += ".\n"
        }
    } else {
        description = " "
    }

    message.parameters.each {parameter ->
        description += "- *${parameter.name}* = ${parameter.description} \n"
    }

    wikiFile.append("|${code}|${text.replace("{", "\\{")}|${description.replace("{", "\\{")}|\n")
}
logger.info("Extraction of Error Messages completed.")
logger.info("Generated Wiki Import File: ${wikiFile.absoluteFile}")
