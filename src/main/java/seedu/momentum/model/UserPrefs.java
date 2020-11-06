package seedu.momentum.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.momentum.commons.core.GuiThemeSettings;
import seedu.momentum.commons.core.GuiWindowSettings;
import seedu.momentum.commons.core.StatisticTimeframeSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiWindowSettings guiWindowSettings = new GuiWindowSettings();
    private GuiThemeSettings guiThemeSettings = new GuiThemeSettings();
    private StatisticTimeframeSettings statisticTimeframeSettings = new StatisticTimeframeSettings();
    private Path projectBookFilePath = Paths.get("data" , "projectbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    private UserPrefs(GuiWindowSettings guiWindowSettings,
                      GuiThemeSettings guiThemeSettings,
                      StatisticTimeframeSettings statisticTimeframeSettings,
                      Path projectBookFilePath) {
        this.guiWindowSettings = guiWindowSettings;
        this.guiThemeSettings = guiThemeSettings;
        this.statisticTimeframeSettings = statisticTimeframeSettings;
        this.projectBookFilePath = projectBookFilePath;
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiWindowSettings(newUserPrefs.getGuiWindowSettings());
        setGuiThemeSettings(newUserPrefs.getGuiThemeSettings());
        setStatisticTimeframeSettings(newUserPrefs.getStatisticTimeframeSettings());
        setProjectBookFilePath(newUserPrefs.getProjectBookFilePath());
    }

    public GuiWindowSettings getGuiWindowSettings() {
        return guiWindowSettings;
    }

    public GuiThemeSettings getGuiThemeSettings() {
        return guiThemeSettings;
    }

    public StatisticTimeframeSettings getStatisticTimeframeSettings() {
        return statisticTimeframeSettings;
    }

    public Path getProjectBookFilePath() {
        return projectBookFilePath;
    }

    public void setGuiWindowSettings(GuiWindowSettings guiWindowSettings) {
        requireNonNull(guiWindowSettings);
        this.guiWindowSettings = guiWindowSettings;
    }

    public void setGuiThemeSettings(GuiThemeSettings guiThemeSettings) {
        requireNonNull(guiThemeSettings);
        this.guiThemeSettings = guiThemeSettings;
    }

    public void setStatisticTimeframeSettings(StatisticTimeframeSettings statisticTimeframeSettings) {
        requireNonNull(statisticTimeframeSettings);
        this.statisticTimeframeSettings = statisticTimeframeSettings;
    }

    public void setProjectBookFilePath(Path projectBookFilePath) {
        requireNonNull(projectBookFilePath);
        this.projectBookFilePath = projectBookFilePath;
    }

    /**
     * Returns a new {@code UserPrefs} updated with the {@code newGuiThemeSetings}.
     *
     * @param newGuiThemeSettings new theme settings to be applied.
     * @return the new {@code UserPrefs}.
     */
    public UserPrefs returnChangedGuiThemeSettings(GuiThemeSettings newGuiThemeSettings) {
        requireNonNull(newGuiThemeSettings);
        return new UserPrefs(this.guiWindowSettings, newGuiThemeSettings,
            this.statisticTimeframeSettings, this.projectBookFilePath);
    }

    /**
     * Returns a new {@code UserPrefs} updated with the {@code newStatisticTimeframeSetings}.
     *
     * @param newTimeframeSettings new statistic timeframe settings to be applied.
     * @return the new {@code UserPrefs}.
     */
    public UserPrefs returnChangedStatisticsTimeframeSettings(StatisticTimeframeSettings newTimeframeSettings) {
        requireNonNull(newTimeframeSettings);
        return new UserPrefs(this.guiWindowSettings, this.guiThemeSettings,
            newTimeframeSettings, this.projectBookFilePath);
    }

    /**
     * Returns a new {@code UserPrefs} updated with the {@code newProjectBookFilePath}.
     *
     * @param newProjectBookFilePath new statistic timeframe settings to be applied.
     * @return the new {@code UserPrefs}.
     */
    public UserPrefs returnChangedProjectBookFilePath(Path newProjectBookFilePath) {
        requireNonNull(newProjectBookFilePath);
        return new UserPrefs(this.guiWindowSettings, this.guiThemeSettings,
            this.statisticTimeframeSettings, newProjectBookFilePath);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiWindowSettings.equals(o.guiWindowSettings)
                && guiThemeSettings.equals(o.guiThemeSettings)
                && statisticTimeframeSettings.equals(o.statisticTimeframeSettings)
                && projectBookFilePath.equals(o.projectBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiWindowSettings, guiThemeSettings,
            statisticTimeframeSettings, projectBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui window settings : " + guiWindowSettings);
        sb.append("Gui theme settings : " + guiThemeSettings);
        sb.append("Statistic timeframe settings : " + statisticTimeframeSettings);
        sb.append("\nLocal data file location : " + projectBookFilePath);
        return sb.toString();
    }

}
