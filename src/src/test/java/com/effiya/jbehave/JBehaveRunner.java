package com.effiya.jbehave;

import java.util.List;
import java.util.Locale;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.failures.RethrowingFailure;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.AbsolutePathCalculator;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.effiya.jbehave.steps.Scenario1Transaction;
import com.thoughtworks.paranamer.NullParanamer;

public class JBehaveRunner extends JUnitStories {
	
	private Configuration configuration;
	
	@Autowired
    private ApplicationContext appContext;
	
	public JBehaveRunner() {
		super();
		configuration = new Configuration() {
		};
		configuration.useStoryReporterBuilder(
				new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML, Format.STATS, Format.XML));

		configuration.useFailureStrategy(new RethrowingFailure());
		configuration.useKeywords(new LocalizedKeywords(Locale.ENGLISH));
		configuration.usePathCalculator(new AbsolutePathCalculator());
		configuration.useParameterControls(new ParameterControls());
		configuration.useParameterConverters(new ParameterConverters());
		configuration.useParanamer(new NullParanamer());
		configuration.usePendingStepStrategy(new PassingUponPendingStep());
		configuration.useStepCollector(new MarkUnmatchedStepsAsPending());
		configuration.useStepdocReporter(new PrintStreamStepdocReporter());
		configuration.useStepFinder(new StepFinder());
		configuration.useStepMonitor(new SilentStepMonitor());
		configuration.useStepPatternParser(new RegexPrefixCapturingPatternParser());
		configuration.useStoryControls(new StoryControls());
		configuration.useStoryParser(new RegexStoryParser(configuration.keywords()));
		configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());
		configuration.useViewGenerator(new FreemarkerViewGenerator());
		configuration.useStoryLoader(new LoadFromClasspath());

		EmbedderControls embedderControls = configuredEmbedder().embedderControls();
		embedderControls.doBatch(false);
		embedderControls.doGenerateViewAfterStories(true);
		embedderControls.doIgnoreFailureInStories(false);
		embedderControls.doIgnoreFailureInView(false);
		embedderControls.doSkip(false);
		embedderControls.doVerboseFailures(false);
		embedderControls.doVerboseFiltering(false);
		embedderControls.useStoryTimeouts("300");
		embedderControls.useThreads(1);
	}

	@Override
	public Configuration configuration() {
		return configuration;
	}
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new Scenario1Transaction());
	}
	
	/*public JBehaveRunner() {
		super();
		this.configuredEmbedder().candidateSteps().add(new Scenario1Transaction());
	}*/

	/*public Configuration cofiguration() {
		return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(getClass().getClassLoader())).useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE, Format.STATS, Format.HTML));
	}*/
	
	
	
	/*public JBehaveRunner() {
        Class<?> thisClass = this.getClass();
        Properties properties = new Properties();
        properties.setProperty("encoding", "UTF-8");
        // @formatter:off
        useConfiguration(new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(thisClass.getClassLoader()))
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStepdocReporter(new PrintStreamStepdocReporter())
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(thisClass))
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML, Format.STATS)
                        .withCrossReference(new CrossReference())
                        .withViewResources(properties)
                        .withFailureTrace(true))
                .useParameterConverters(new ParameterConverters()
                        .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("dd-MM-yyyy"))))
                .useStoryParser(new GherkinStoryParser())
                .useParameterControls(new ParameterControls().useNameDelimiterLeft("[").useNameDelimiterRight("]"))
                .useStepMonitor(new SilentStepMonitor()));
        // @formatter:on
    }*/

	/*@Override
	public List<CandidateSteps> candidateSteps(){
		return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
	}*/
	
	/*@Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), appContext);
    }*/

	/*@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/effiya/jbehave/stories/scenario1.story");
	}*/

	protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
    }




}
