package com.ciastkaipiwo.android.scrummajster;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 0;

    private List<Project> mProjectList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProjectsAdapter mProjectsAdapter;
    private FloatingActionButton mAddButton;

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView.setAdapter(mProjectsAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mProjectsAdapter = new ProjectsAdapter(mProjectList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProjectsAdapter);

        mAddButton = (FloatingActionButton) findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProjectConfigActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }

        });

        initProjectsData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
                return;
        }
        if (requestCode == REQUEST_CODE_ADD) {
            if (data == null) {
                return;
            }
            mProjectList.add(ProjectConfigActivity.getNewProject(data));

        }
    }

    private void initProjectsData() {
        for (int i = 1; i<3; i++) {
            mProjectList.add(new Project("Project"+i, new GregorianCalendar(2018,03,31), new GregorianCalendar(2018,04,i)));
        }
    }

}