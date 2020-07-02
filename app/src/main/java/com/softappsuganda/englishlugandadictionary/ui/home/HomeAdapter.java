package com.softappsuganda.englishlugandadictionary.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.l4digital.fastscroll.FastScroller;
import com.softappsuganda.englishlugandadictionary.R;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.extjwnl.dictionary.Dictionary;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements FastScroller.SectionIndexer {
    List<String> words =  new ArrayList<>(Arrays.asList("aello","bello","cello","dello","ello","fello","gello","hello"));
    public HomeAdapter(){
//        initializeDictionary();
//        for(int i=0;i<200;i++){
//            words.add(String.valueOf(i));
//        }
    }

    private void initializeDictionary() {
//        ExecutorService executors = Executors.newSingleThreadExecutor();
//        executors.execute(new Runnable() {
//            @Override
//            public void run() {
                try {
                    Dictionary dictionary = Dictionary.getDefaultResourceInstance();
                    IndexWord funny = dictionary.lookupIndexWord(POS.NOUN, "funny");
                    Log.d("WORD",funny.toString());
                    Log.d("WORD",funny.getLemma());
                    List<Synset> senses = funny.getSenses();
                    for(Synset synset: senses){
                        Log.d("DESC",synset.getGloss());
                    }
                    Iterator<IndexWord> itera = dictionary.getIndexWordIterator(POS.NOUN);
                    int i  =0;
                    while (itera.hasNext()){
                        if(i==20)break;
                        IndexWord indexWord = itera.next();
                        words.add(indexWord.getLemma());
                        Log.d("WORD",indexWord.getLemma());


                        notifyDataSetChanged();
                    }

                } catch (JWNLException e) {
                    e.printStackTrace();
                }
            }
//        });
//
//
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.word.setText(words.get(position));

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    @Override
    public CharSequence getSectionText(int position) {
        return String.valueOf(words.get(position).charAt(0));

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView word;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word_text);
        }
    }
}
