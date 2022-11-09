package es.unex.fulltank;

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * La idea de esta clase es abstraer el funcionamiento de los hilos en Java.
 * @author Grupo PGD02.
 * @version 1.0
 */
public class AppExecutors {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor diskIO;
    private final Executor mainThread;
    private final Executor networkIO;

    /**
     * Inicializa los hilos de disco que vienen dados por parámetro.
     * @param diskIO
     * @param networkIO
     * @param mainThread
     */
    private AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    /**
     * Esta clase implementa el patrón singleton
     * @return la instancia de esta clase
     */
    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor());
            }
        }
        return sInstance;
    }

    /**
     * @return El ejecutor de disco
     */
    public Executor diskIO() {
        return diskIO;
    }

    /**
     * @return El hilo ejecutor principal.
     */
    public Executor mainThread() {
        return mainThread;
    }

    /**
     * @return El ejecutor para operaciones relacionadas con datos en la nube.
     */
    public Executor networkIO() {
        return networkIO;
    }

    /**
     * Esta es una clase auxiliar que implementa el metodo execute de los ejecutores.
     */
    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
