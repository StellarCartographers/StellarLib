/*
 * Copyright (c) 2020 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package space.tscg.common.util;

import java.io.File;
import java.io.IOException;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class YamlUtils {

    private static final Representer REPRESENTER = new Representer(new DumperOptions()) {{
        this.getPropertyUtils().setSkipMissingProperties(true);
    }};
        
    private static final Yaml YAML = new Yaml(REPRESENTER);

    public static <T> T load(String data, Class<T> type) {
        return YAML.loadAs(data, type);
    }

    public static <T> T load(File file, Class<T> type) throws IOException {
        return YAML.loadAs(FileUtility.getContentOfFile(file), type);
    }
}
