package org.guides.dialogs.app;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDialog();
            }
        });

        findViewById(R.id.simple_dialog_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithTitle();
            }
        });

        findViewById(R.id.simple_dialog_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithList();
            }
        });

        findViewById(R.id.simple_dialog_custom_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithCustomList();
            }
        });

        findViewById(R.id.simple_dialog_multi_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithMultiList();
            }
        });

        findViewById(R.id.simple_dialog_single_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithSingleList();
            }
        });

        findViewById(R.id.simple_dialog_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithTime();
            }
        });

        findViewById(R.id.simple_dialog_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWithDate();
            }
        });

        findViewById(R.id.simple_dialog_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });

        findViewById(R.id.custom_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }


    private void showSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.hello_world)
                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showSimpleDialog() - positive clicked");
                    }
                })
                .setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showSimpleDialog() - negative clicked");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogWithTitle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hello_world_title);
        builder.setMessage(R.string.hello_world)
                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showDialogWithTitle() - positive clicked");
                    }
                })
                .setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showDialogWithTitle() - negative clicked");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogWithList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hello_world_title);

        builder.setItems(R.array.hello_world, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Log.d(TAG, "showDialogWithList() - selected element at " + position);
                String[] strings = getResources().getStringArray(R.array.hello_world);
                Log.d(TAG, "showDialogWithList() - selected element " + strings[position]);
            }
        });
        final AlertDialog ld = builder.create();
        ld.show();
    }

    private void showDialogWithCustomList() {

        class HelloAdapter extends ArrayAdapter<String> {

            public HelloAdapter(Context context, String[] objects) {
                super(context, 0, objects);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(getContext());
                    convertView.setPadding(16, 16, 16, 16);
                }
                TextView textView = (TextView) convertView;
                String value = getItem(position);
                textView.setText(value);
                return convertView;
            }
        }

        String[] strings = getResources().getStringArray(R.array.hello_world);
        final HelloAdapter adapter = new HelloAdapter(this, strings);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hello_world_title);

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Log.d(TAG, "showDialogWithCustomList() - selected element at " + position);
                Log.d(TAG, "showDialogWithCustomList() - selected element " + adapter.getItem(position));
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogWithMultiList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hello_world_title);

        final Set<Integer> selectedItems = new HashSet<Integer>();

        builder.setMultiChoiceItems(R.array.hello_world, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })

                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showDialogWithMultiList() - positive clicked");

                        Log.d(TAG, "showDialogWithMultiList() - selected items " + selectedItems);

                        String[] strings = getResources().getStringArray(R.array.hello_world);

                        StringBuilder selected = new StringBuilder();
                        selected.append("[");
                        Iterator<Integer> iterator = selectedItems.iterator();
                        while (iterator.hasNext()) {
                            Integer index = iterator.next();
                            selected.append(strings[index]);
                            if (iterator.hasNext()) {
                                selected.append(" , ");
                            }
                        }

                        selected.append("]");
                        Log.d(TAG, "showDialogWithMultiList() - selected items " + selected.toString());
                    }
                })
                .setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showDialogWithMultiList() - negative clicked");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogWithSingleList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hello_world_title);

        final int[] selectedItem = {0};

        builder.setSingleChoiceItems(R.array.hello_world, selectedItem[0],
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedItem[0] = which;
                    }
                })

                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showDialogWithSingleList() - positive clicked");

                        Log.d(TAG, "showDialogWithSingleList() - selected item " + selectedItem[0]);

                        String[] strings = getResources().getStringArray(R.array.hello_world);

                        Log.d(TAG, "showDialogWithSingleList() - selected item " + strings[selectedItem[0]]);
                    }
                })
                .setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d(TAG, "showDialogWithSingleList() - negative clicked");
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogWithTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "showDialogWithTime() - time set at " + hourOfDay + " and " + minute + " minutes");
            }
        }, hour, minute,
                DateFormat.is24HourFormat(this));
        dialog.show();
    }

    private void showDialogWithDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "showDialogWithTime() - date set at " + year + " and " + monthOfYear + " and " + dayOfMonth);
            }
        }, year, monthOfYear, dayOfMonth);
        dialog.show();
    }

    private void showProgressDialog() {
        final ProgressDialog dialog = ProgressDialog.show(this, getString(R.string.hello_world_title),
                getString(R.string.hello_world), true, true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, 3000);
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.hello_world_title);

        View view = View.inflate(this, R.layout.custom_dialog, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(TAG, "showCustomDialog() - positive clicked");
            }
        });
        builder.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(TAG, "showCustomDialog() - negative clicked");
            }
        });
        final AlertDialog ld = builder.create();
        ld.show();
    }
}
