package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

public class TestHelper {

    private static HeadlessApplication application;

    @BeforeAll
    public static void setupLibGDX() {
        if (application == null) {
            Gdx.gl = Mockito.mock(GL20.class);
            Gdx.gl20 = Mockito.mock(GL20.class);
            application = new HeadlessApplication(new ApplicationAdapter() {});
        }
    }
}
