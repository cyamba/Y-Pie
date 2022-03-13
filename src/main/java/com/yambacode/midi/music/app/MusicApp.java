package com.yambacode.midi.music.app;


import com.yambacode.midi.music.model.Melody;

/**
 * @author Christopher Yamba
 */
public class MusicApp {

    private int tempo;

    private MusicApp(int tempo) {
        this.tempo = tempo;
    }

    public void play(Melody melody){

    }

    public static class Builder{

        private int tempo;

        private Builder(int tempo) {
            this.tempo = tempo;
        }

        public Builder withTempo(int tempo){
            this.tempo = tempo;
            return this;
        }

        public MusicApp build(){
            return new MusicApp(tempo);
        }
    }

    public int getTempo() {
        return tempo;
    }
}
