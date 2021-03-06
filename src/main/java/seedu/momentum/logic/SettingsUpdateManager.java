package seedu.momentum.logic;

import seedu.momentum.commons.core.StatisticTimeframe;
import seedu.momentum.commons.core.Theme;
import seedu.momentum.logic.statistic.StatisticGenerator;
import seedu.momentum.ui.Ui;

/**
 * Class that helps to update application interface when settings change.
 */
public class SettingsUpdateManager {

    private static Ui ui;
    private static StatisticGenerator statistic;

    /**
     * Initializes the {@code Ui} and {@code StatisticGenerator} to be maintained.
     */
    public static void initSettingsUpdateManager(Ui appUi, StatisticGenerator appStatistic) {
        ui = appUi;
        statistic = appStatistic;
    }

    /**
     * Updates the theme of the application.
     */
    public static void updateTheme(Theme theme) {
        ui.getMainWindow().updateTheme(theme);
    }

    /**
     * Updates the timeframe for the statistics tracked.
     */
    public static void updateStatisticTimeframe(StatisticTimeframe timeframe) {
        statistic.updateStatisticTimeframe(timeframe);
        ui.getMainWindow().updateStatList();
    }

}
