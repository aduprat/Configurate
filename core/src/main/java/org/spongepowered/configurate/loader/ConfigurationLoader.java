/*
 * Configurate
 * Copyright (C) zml and Configurate contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spongepowered.configurate.loader;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurationNodeFactory;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.WatchServiceListener;

import java.nio.file.Path;
import java.util.function.Function;

/**
 * Represents an object which can load and save {@link ConfigurationNode} objects in a specific
 * configuration format.
 *
 * <p>An abstract implementation is provided by {@link AbstractConfigurationLoader}.</p>
 *
 * @param <N> the {@link ConfigurationNode} type produced by the loader
 */
public interface ConfigurationLoader<N extends ConfigurationNode> extends ConfigurationNodeFactory<N> {

    /**
     * Attempts to load a {@link ConfigurationNode} using this loader, from the
     * defined source.
     *
     * <p>The resultant node represents the root of the configuration being
     * loaded.</p>
     *
     * <p>The {@link #defaultOptions() default options} will be used to
     * construct the resultant configuration nodes.</p>
     *
     * @return the newly constructed node
     * @throws ConfigurateException if any sort of error occurs with reading or
     *                              parsing the configuration
     */
    default N load() throws ConfigurateException {
        return load(defaultOptions());
    }

    /**
     * Attempts to load a {@link ConfigurationNode} using this loader, from the defined source.
     *
     * <p>The resultant node represents the root of the configuration being
     * loaded.</p>
     *
     * @param options the options to load with
     * @return the newly constructed node
     * @throws ConfigurateException if any sort of error occurs with reading or
     *                              parsing the configuration
     */
    N load(ConfigurationOptions options) throws ConfigurateException;

    /**
     * Attempts to load data from the defined source into a {@link ConfigurationReference}.
     * The returned reference will not reload automatically.
     *
     * @return the created reference
     * @throws ConfigurateException when an error occurs within the loader
     * @see WatchServiceListener#listenToConfiguration(Function, Path) to
     *      create an auto-reloading configuration.
     */
    ConfigurationReference<N> loadToReference() throws ConfigurateException;

    /**
     * Attempts to save a {@link ConfigurationNode} using this loader, to the defined sink.
     *
     * @param node the node to save
     * @throws ConfigurateException if any sort of error occurs with writing or
     *                     generating the configuration
     */
    void save(ConfigurationNode node) throws ConfigurateException;

    /**
     * Gets if this loader is capable of loading configurations.
     *
     * @return if this loader can load
     */
    default boolean canLoad() {
        return true;
    }

    /**
     * Gets if this loader is capable of saving configurations.
     *
     * @return if this loader can save
     */
    default boolean canSave() {
        return true;
    }

}
