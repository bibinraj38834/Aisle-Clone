package com.example.aisleclone.screens.ui.notes;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.renderscript.Allocation;
import androidx.renderscript.Element;
import androidx.renderscript.RenderScript;
import androidx.renderscript.ScriptIntrinsicBlur;

import com.example.aisleclone.databinding.FragmentNotesBinding;
import com.example.aisleclone.models.notes.NotesResponseModel;
import com.example.aisleclone.network.NetworkAPI;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private NotesResponseModel notesResponseModel = new NotesResponseModel();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotesViewModel notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.loading.setVisibility(View.VISIBLE);
        notesViewModel.fetchNotes();
        notesViewModel.getnotesResponse().observe(getViewLifecycleOwner(), new Observer<NotesResponseModel>() {
            @Override
            public void onChanged(NotesResponseModel notesResponseModel) {

                binding.loading.setVisibility(View.GONE);
                Picasso.get().load(notesResponseModel.getLikes().getProfiles().get(0).getAvatarUrl()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        binding.likesImage1.setImageBitmap(blurBitmap(bitmap, 25));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                Picasso.get().load(notesResponseModel.getLikes().getProfiles().get(1).getAvatarUrl()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        binding.likesImage2.setImageBitmap(blurBitmap(bitmap, 25));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

                Picasso.get().load(notesResponseModel.getInvites().getProfiles().get(0).getPhotos().get(0).getImageUrl()).into(binding.invitesImage);

                String invitesDetail = notesResponseModel.getInvites().getProfiles().get(0).getGeneralInformation().getFirstName()
                        + ", " + notesResponseModel.getInvites().getProfiles().get(0).getGeneralInformation().getAge();
                binding.invitesName.setText(invitesDetail);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Bitmap blurBitmap(Bitmap bitmap, float blurRadius) {
        // Use the RenderScript API to apply the blur effect
        Bitmap blurredBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript renderScript = RenderScript.create(requireContext());
        Allocation input = Allocation.createFromBitmap(renderScript, bitmap);
        Allocation output = Allocation.createFromBitmap(renderScript, blurredBitmap);

        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        script.setInput(input);
        script.setRadius(blurRadius);
        script.forEach(output);
        output.copyTo(blurredBitmap);

        // Release the resources
        renderScript.destroy();

        return blurredBitmap;
    }
}