package de.adesso.azubis.iliasdownloader.v3.ui.intro.setup;

import de.adesso.azubis.iliasdownloader.v3.ui.util.DialogHelper;
import de.adesso.iliasdownloader3.service.IliasService;
import de.adesso.iliasdownloader3.service.webparser.WebParserIliasServiceProvider;
import javafx.scene.control.TextInputDialog;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

/**
 * @author broj
 * @since 25.09.2016
 */
@Log4j2
public final class WebIliasSetupController {

    public Optional<IliasService> getIliasService(@NonNull String loginPage) {
        if (loginPage.isEmpty()) {
            Optional<String> loginOptional = getLoginPage();
            if (loginOptional.isPresent()) {
                loginPage = loginOptional.get();
            } else {
                // user has canceled
                return Optional.empty();
            }
        }

        try {
            WebParserIliasServiceProvider serviceProvider = new WebParserIliasServiceProvider(loginPage);
            return Optional.of(serviceProvider.newInstance());
        } catch (Exception e) {
            log.error("Konnte den Ilias Service Provider nicht erstellen", e);
            DialogHelper.showExceptionDialog("Fehler beim Erstellen des Ilias Service Providers", e);
        }
        return getIliasService("");
    }

    private Optional<String> getLoginPage() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Ilias Login Seite eingeben");
        inputDialog.setTitle("Ilias Downloader 3 - Einrichtung");
        return inputDialog.showAndWait();
    }

}
