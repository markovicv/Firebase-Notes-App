package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasenotesapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import model.Note;

public class NotesAdapter extends FirestoreRecyclerAdapter<Note, NotesAdapter.NoteHolder> {

    public NotesAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder noteHolder, int i, @NonNull Note note) {
        noteHolder.titleTxt.setText(note.getTitle());
        noteHolder.descriptionTxt.setText(note.getDescription());
        noteHolder.priorityTxt.setText(String.valueOf(note.getPriority()));
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);

        return new NoteHolder(v);
    }
    public void deleteNote(int pos){
        getSnapshots().getSnapshot(pos).getReference().delete();
    }

    class NoteHolder extends RecyclerView.ViewHolder{

        private TextView titleTxt;
        private TextView descriptionTxt;
        private TextView priorityTxt;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            this.titleTxt = itemView.findViewById(R.id.titletvId);
            this.descriptionTxt = itemView.findViewById(R.id.descriptionId);
            this.priorityTxt = itemView.findViewById(R.id.prioritytvId);
        }

    }
}
