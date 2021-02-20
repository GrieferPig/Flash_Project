package com.grieferpig.flash;

enum configOptions {
    emerSoundEnabled("com.grieferpig.flash.storage.emerSoundEnabled");

    public String value = "";

    configOptions(String string) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
