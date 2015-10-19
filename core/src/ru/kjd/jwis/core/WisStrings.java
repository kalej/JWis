package ru.kjd.jwis.core;

import ru.kjd.jwis.core.enums.Language;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
/**
 * Created by anonymous on 10/19/15.
 */
public class WisStrings {
    public static final String STRING_WORKSHOP_INFORMATION_SYSTEM = "string.workshop.information.system";
    public static final String STRING_DOCUMENT_SEARCH = "string.document.search";
    public static final String STRING_DOCUMENT_SEARCH_TIP = "string.document.search.tip";
    public static final String STRING_FORWARD = "string.forward";
    public static final String STRING_FORWARD_TIP = "string.forward.tip";
    public static final String STRING_BACK = "string.back";
    public static final String STRING_BACK_TIP = "string.back.tip";
    public static final String STRING_USER_NOTE = "string.user.note";
    public static final String STRING_USER_NOTE_TIP = "string.user.note.tip";
    public static final String STRING_SPARE_PARTS = "string.spare.parts";
    public static final String STRING_SPARE_PARTS_TIP = "string.spare.parts.tip";
    public static final String STRING_DIAGNOSTIC_PROCEDURE = "string.diagnostic.procedure";
    public static final String STRING_DIAGNOSTIC_PROCEDURE_TIP = "string.diagnostic.procedure.tip";
    public static final String STRING_ADD_BOOKMARK = "string.add.bookmark";
    public static final String STRING_ADD_BOOKMARK_TIP = "string.add.bookmark.tip";
    public static final String STRING_EMPTY = "string.empty";
    public static final String STRING_SYMPTOM_SEARCH = "string.symptom.search";
    public static final String STRING_SYMPTOM_SEARCH_TIP = "string.symptom.search.tip";
    public static final String STRING_MODEL_SELECTION = "string.model.selection";
    public static final String STRING_MODEL_SELECTION_TIP = "string.model.selection.tip";
    public static final String STRING_CHANGE_CAR_MODEL_OR_YEAR = "string.change.car.model.or.year";
    public static final String STRING_CHANGE_CAR_MODEL_OR_YEAR_TIP = "string.change.car.model.or.year.tip";
    public static final String STRING_HISTORY = "string.history";
    public static final String STRING_HISTORY_TIP = "string.history.tip";
    public static final String STRING_NAVIGATION_MODE = "string.navigation.mode";
    public static final String STRING_NAVIGATION_MODE_TIP = "string.navigation.mode.tip";
    public static final String STRING_BROWSER_MODE = "string.browser.mode";
    public static final String STRING_BROWSER_MODE_TIP = "string.browser.mode.tip";
    public static final String STRING_BOOKMARKS = "string.bookmarks";
    public static final String STRING_BOOKMARKS_TIP = "string.bookmarks.tip";
    public static final String STRING_RELEASE_NOTES = "string.release.notes";
    public static final String STRING_RELEASE_NOTES_TIP = "string.release.notes.tip";
    public static final String STRING_BACK_A = "string.back.a";
    public static final String STRING_BACK_A_TIP = "string.back.a.tip";
    public static final String STRING_PRINT = "string.print";
    public static final String STRING_PRINT_TIP = "string.print.tip";
    public static final String STRING_BACK_B = "string.back.b";
    public static final String STRING_BACK_B_TIP = "string.back.b.tip";
    public static final String STRING_TECH_2_NAVIGATOR = "string.tech.2.navigator";
    public static final String STRING_TECH_2_NAVIGATOR_TIP = "string.tech.2.navigator.tip";
    public static final String STRING_READ_FAULT_CODES = "string.read.fault.codes";
    public static final String STRING_READ_FAULT_CODES_TIP = "string.read.fault.codes.tip";
    public static final String STRING_EPSI_HELPDESK = "string.epsi.helpdesk";
    public static final String STRING_EPSI_HELPDESK_TIP = "string.epsi.helpdesk.tip";
    public static final String STRING_FAULT_CODE_SEARCH = "string.fault.code.search";
    public static final String STRING_FAULT_CODE_SEARCH_TIP = "string.fault.code.search.tip";
    public static final String STRING_FAULT_TRACE_MODE = "string.fault.trace.mode";
    public static final String STRING_FAULT_TRACE_MODE_TIP = "string.fault.trace.mode.tip";
    public static final String STRING_CLOSE = "string.close";
    public static final String STRING_CLOSE_TIP = "string.close.tip";
    public static final String STRING_DIAGNOSTIC_SETTINGS = "string.diagnostic.settings";
    public static final String STRING_DIAGNOSTIC_SETTINGS_TIP = "string.diagnostic.settings.tip";
    public static final String STRING_WIS = "string.wis";
    public static final String STRING_READY = "string.ready";
    public static final String STRING_ERASE_THE_SELECTION = "string.erase.the.selection";
    public static final String STRING_ERASE_THE_SELECTION_TIP = "string.erase.the.selection.tip";
    public static final String STRING_ERASE_EVERYTHING = "string.erase.everything";
    public static final String STRING_ERASE_EVERYTHING_TIP = "string.erase.everything.tip";
    public static final String STRING_COPY_THE_SELECTION_AND_PUT_IT_ON_THE_CLIPBOARD = "string.copy.the.selection.and.put.it.on.the.clipboard";
    public static final String STRING_COPY_THE_SELECTION_AND_PUT_IT_ON_THE_CLIPBOARD_TIP = "string.copy.the.selection.and.put.it.on.the.clipboard.tip";
    public static final String STRING_CUT_THE_SELECTION_AND_PUT_IT_ON_THE_CLIPBOARD = "string.cut.the.selection.and.put.it.on.the.clipboard";
    public static final String STRING_CUT_THE_SELECTION_AND_PUT_IT_ON_THE_CLIPBOARD_TIP = "string.cut.the.selection.and.put.it.on.the.clipboard.tip";
    public static final String STRING_FIND_THE_SPECIFIED_TEXT = "string.find.the.specified.text";
    public static final String STRING_FIND_THE_SPECIFIED_TEXT_TIP = "string.find.the.specified.text.tip";
    public static final String STRING_INSERT_CLIPBOARD_CONTENTS = "string.insert.clipboard.contents";
    public static final String STRING_INSERT_CLIPBOARD_CONTENTS_TIP = "string.insert.clipboard.contents.tip";
    public static final String STRING_REPEAT_THE_LAST_ACTION = "string.repeat.the.last.action";
    public static final String STRING_REPEAT_THE_LAST_ACTION_TIP = "string.repeat.the.last.action.tip";
    public static final String STRING_REPLACE_SPECIFIC_TEXT_WITH_DIFFERENT_TEXT = "string.replace.specific.text.with.different.text";
    public static final String STRING_REPLACE_SPECIFIC_TEXT_WITH_DIFFERENT_TEXT_TIP = "string.replace.specific.text.with.different.text.tip";
    public static final String STRING_SELECT_THE_ENTIRE_DOCUMENT = "string.select.the.entire.document";
    public static final String STRING_SELECT_THE_ENTIRE_DOCUMENT_TIP = "string.select.the.entire.document.tip";
    public static final String STRING_UNDO_THE_LAST_ACTION = "string.undo.the.last.action";
    public static final String STRING_UNDO_THE_LAST_ACTION_TIP = "string.undo.the.last.action.tip";
    public static final String STRING_REDO_THE_PREVIOUSLY_UNDONE_ACTION = "string.redo.the.previously.undone.action";
    public static final String STRING_REDO_THE_PREVIOUSLY_UNDONE_ACTION_TIP = "string.redo.the.previously.undone.action.tip";
    public static final String STRING_SPLIT_THE_ACTIVE_WINDOW_INTO_PANES = "string.split.the.active.window.into.panes";
    public static final String STRING_SPLIT_THE_ACTIVE_WINDOW_INTO_PANES_TIP = "string.split.the.active.window.into.panes.tip";
    public static final String STRING_DISPLAY_PROGRAM_INFORMATION_VERSION_NUMBER_AND_COPYRIGHT = "string.display.program.information.version.number.and.copyright";
    public static final String STRING_DISPLAY_PROGRAM_INFORMATION_VERSION_NUMBER_AND_COPYRIGHT_TIP = "string.display.program.information.version.number.and.copyright.tip";
    public static final String STRING_QUIT_THE_APPLICATION_PROMPTS_TO_SAVE_DOCUMENTS = "string.quit.the.application.prompts.to.save.documents";
    public static final String STRING_QUIT_THE_APPLICATION_PROMPTS_TO_SAVE_DOCUMENTS_TIP = "string.quit.the.application.prompts.to.save.documents.tip";
    public static final String STRING_SWITCH_TO_THE_NEXT_WINDOW_PANE = "string.switch.to.the.next.window.pane";
    public static final String STRING_SWITCH_TO_THE_NEXT_WINDOW_PANE_TIP = "string.switch.to.the.next.window.pane.tip";
    public static final String STRING_SWITCH_BACK_TO_THE_PREVIOUS_WINDOW_PANE = "string.switch.back.to.the.previous.window.pane";
    public static final String STRING_SWITCH_BACK_TO_THE_PREVIOUS_WINDOW_PANE_TIP = "string.switch.back.to.the.previous.window.pane.tip";
    public static final String STRING_EXT = "string.ext";
    public static final String STRING_CAP = "string.cap";
    public static final String STRING_NUM = "string.num";
    public static final String STRING_SCRL = "string.scrl";
    public static final String STRING_OVR = "string.ovr";
    public static final String STRING_REC = "string.rec";
    public static final String STRING_SHOW_OR_HIDE_THE_TOOLBAR = "string.show.or.hide.the.toolbar";
    public static final String STRING_SHOW_OR_HIDE_THE_TOOLBAR_TIP = "string.show.or.hide.the.toolbar.tip";
    public static final String STRING_SHOW_OR_HIDE_THE_STATUS_BAR = "string.show.or.hide.the.status.bar";
    public static final String STRING_SHOW_OR_HIDE_THE_STATUS_BAR_TIP = "string.show.or.hide.the.status.bar.tip";
    public static final String STRING_CHANGE_THE_WINDOW_SIZE = "string.change.the.window.size";
    public static final String STRING_CHANGE_THE_WINDOW_POSITION = "string.change.the.window.position";
    public static final String STRING_REDUCE_THE_WINDOW_TO_AN_ICON = "string.reduce.the.window.to.an.icon";
    public static final String STRING_ENLARGE_THE_WINDOW_TO_FULL_SIZE = "string.enlarge.the.window.to.full.size";
    public static final String STRING_SWITCH_TO_THE_NEXT_DOCUMENT_WINDOW = "string.switch.to.the.next.document.window";
    public static final String STRING_SWITCH_TO_THE_PREVIOUS_DOCUMENT_WINDOW = "string.switch.to.the.previous.document.window";
    public static final String STRING_CLOSE_THE_ACTIVE_WINDOW_AND_PROMPTS_TO_SAVE_THE_DOCUMENTS = "string.close.the.active.window.and.prompts.to.save.the.documents";
    public static final String STRING_RESTORE_THE_WINDOW_TO_NORMAL_SIZE = "string.restore.the.window.to.normal.size";
    public static final String STRING_ACTIVATE_TASK_LIST = "string.activate.task.list";
    public static final String STRING_MODEL = "string.model";
    public static final String STRING_YEAR = "string.year";
    public static final String STRING_SERVICE_CATEGORY = "string.service.category";
    public static final String STRING_INFORMATION_TYPE = "string.information.type";
    public static final String STRING_DOCUMENT = "string.document";
    public static final String STRING_USERNAME = "string.username";
    public static final String STRING_FULLNAME = "string.fullname";
    public static final String STRING_LANGUAGE = "string.language";
    public static final String STRING_MARKET = "string.market";
    public static final String STRING_FAULT_CODE = "string.fault.code";
    public static final String STRING_SYMPTOM = "string.symptom";
    public static final String STRING_THIS_WILL_DELETE_THE_USER = "string.this.will.delete.the.user";
    public static final String STRING_THIS_WILL_DELETE_THE_USER_TIP = "string.this.will.delete.the.user.tip";
    public static final String STRING_THE_USER_ALREADY_EXISTS = "string.the.user.already.exists.";
    public static final String STRING_THE_USER_ALREADY_EXISTS_TIP = "string.the.user.already.exists..tip";
    public static final String STRING_CHOOSE_MODEL_AND_YEAR = "string.choose.model.and.year";
    public static final String STRING_NAVIGATION_MODE_A = "string.navigation.mode.a";
    public static final String STRING_BROWSER_MODE_A = "string.browser.mode.a";
    public static final String STRING_FAULTTRACE_MODE = "string.faulttrace.mode";
    public static final String STRING_TECHNICAL_DATA = "string.technical.data";
    public static final String STRING_SPECIAL_TOOLS = "string.special.tools";
    public static final String STRING_TECHNICAL_DESCRIPTION = "string.technical.description";
    public static final String STRING_FAULT_DIAGNOSIS_GENERAL = "string.fault.diagnosis.general";
    public static final String STRING_FAULT_DIAGNOSIS_FAULT_CODES = "string.fault.diagnosis.fault.codes";
    public static final String STRING_FAULT_DIAGNOSIS_SYMPTOMS = "string.fault.diagnosis.symptoms";
    public static final String STRING_ADJUSTMENT_REPLACEMENT = "string.adjustment.replacement";
    public static final String STRING_COMPONENT_LOCATION = "string.component.location";
    public static final String STRING_WIRING_DIAGRAM = "string.wiring.diagram";
    public static final String STRING_BULLETINS_SI_MI = "string.bulletins.si.mi";
    public static final String STRING_SCHEDULED_SERVICE = "string.scheduled.service";
    public static final String STRING_ALL = "string.all";
    public static final String STRING_DOCUMENT_SEARCH_A = "string.document.search.a";
    public static final String STRING_SYMPTOM_SEARCH_A = "string.symptom.search.a";
    public static final String STRING_EMPTY_A = "string.empty.a";
    public static final String STRING_BOOKMARKS_A = "string.bookmarks.a";
    public static final String STRING_ERROR_1 = "string.error.1";
    public static final String STRING_ERROR_1_TIP = "string.error.1.tip";
    public static final String STRING_ERROR_2 = "string.error.2";
    public static final String STRING_ERROR_2_TIP = "string.error.2.tip";
    public static final String STRING_PRINT_A = "string.print.a";
    public static final String STRING_CLOSE_A = "string.close.a";
    public static final String STRING_PRINTER = "string.printer";
    public static final String STRING_NAME = "string.name";
    public static final String STRING_STATUS = "string.status";
    public static final String STRING_TYPE = "string.type";
    public static final String STRING_WHERE = "string.where";
    public static final String STRING_COMMENT = "string.comment";
    public static final String STRING_PRINT_TO_FILE = "string.print.to.file";
    public static final String STRING_PRINT_RANGE = "string.print.range";
    public static final String STRING_PROPERTIES = "string.properties";
    public static final String STRING_OK = "string.ok";
    public static final String STRING_CANCEL = "string.cancel";
    public static final String STRING_DOCUMENT_A = "string.document.a";
    public static final String STRING_SCREEN = "string.screen";
    public static final String STRING_NOTES = "string.notes";
    public static final String STRING_FEEDBACK_FORM = "string.feedback.form";
    public static final String STRING_COPIES = "string.copies";
    public static final String STRING_NUMBER_OF_COPIES = "string.number.of.copies";
    public static final String STRING_OWNER = "string.owner";
    public static final String STRING_REG_NO = "string.reg.no";
    public static final String STRING_MODEL_A = "string.model.a";
    public static final String STRING_MILEAGE = "string.mileage";
    public static final String STRING_JOB_NO = "string.job.no";
    public static final String STRING_SCREENSHOOT_WIS = "string.screenshoot.wis";
    public static final String STRING_NOTHING_FOUND = "string.nothing.found";
    public static final String STRING_NOTHING_FOUND_TIP = "string.nothing.found.tip";
    public static final String STRING_HELPFILE_NOT_AVAILABLE = "string.helpfile.not.available";
    public static final String STRING_HELPFILE_NOT_AVAILABLE_TIP = "string.helpfile.not.available.tip";
    public static final String STRING_NO_PACKAGES_WAS_FOUND = "string.no.packages.was.found";
    public static final String STRING_NO_PACKAGES_WAS_FOUND_TIP = "string.no.packages.was.found.tip";
    public static final String STRING_COULD_NOT_FIND = "string.could.not.find";
    public static final String STRING_RELEASENOTE_FOR_THE_CURRENT_MODEL_COULD_NOT_BE_FOUND = "string.releasenote.for.the.current.model.could.not.be.found";
    public static final String STRING_FAILED_TO_OPEN_FILE = "string.failed.to.open.file";
    public static final String STRING_PARSING_FAILED = "string.parsing.failed";
    public static final String STRING_WRONG_CLIENT_VERSION = "string.wrong.client.version";
    public static final String STRING_INVALID_DATE_PLEASE_ENTER_A_NEW_VALID_DATE = "string.invalid.date.please.enter.a.new.valid.date";
    public static final String STRING_THIS_PRODUCT_IS_DISTRIBUTED_UNDER_LICENSES_RESTRICTING_ITS_USE = "string.this.product.is.distributed.under.licenses.restricting.its.use";
    public static final String STRING_THIS_PRODUCT_IS_DISTRIBUTED_UNDER_LICENSES_RESTRICTING_ITS_USE_TIP = "string.this.product.is.distributed.under.licenses.restricting.its.use.tip";
    public static final String STRING_DOCUMENT_NOT_FOUND = "string.document.not.found";
    public static final String STRING_FAULTCODE_SEARCH = "string.faultcode.search";
    public static final String STRING_ECM = "string.ecm";
    public static final String STRING_CM = "string.cm";
    public static final String STRING_TITLE = "string.title";
    public static final String STRING_VEHICLE_SYSTEM = "string.vehicle.system";
    public static final String STRING_FAILED_TO_READ_FAULT_CODES = "string.failed.to.read.fault.codes";
    public static final String STRING_FAILED_TO_READ_FAULT_CODES_TIP = "string.failed.to.read.fault.codes.tip";

    private static Language language = Language.RUSSIA;
    private static Properties properties = new Properties();
    
    static {
        setLanguage(WisProperties.getLanguage());
    }

    public static void setLanguage(Language language){
        WisStrings.language = language;
        try {
            Class c = WisStrings.class;
            ClassLoader cl = c.getClassLoader();
            InputStream is = cl.getResourceAsStream("strings/" + language.getShortName() + ".string.properties");
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
            properties.load(isr);
        } catch (IOException e) {

        }
    }

    public static String get(String name){

        return properties.getProperty(name, name);
    }

    public static String get(String name, boolean doDefault){
        return properties.getProperty(name, doDefault?name:"");
    }
}
