package iberotec.edu.pe.mylooks;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class ProbadorActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mRevealView;
    private boolean hidden = true;
    private ImageButton arriba_btn, abajo_btn, calzado_btn, vestido_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probador);

        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        mRevealView.setVisibility(View.GONE);

        arriba_btn = (ImageButton) findViewById(R.id.arriba);
        abajo_btn = (ImageButton) findViewById(R.id.abajo);
        calzado_btn = (ImageButton) findViewById(R.id.calzado);
        vestido_btn = (ImageButton) findViewById(R.id.vestido);

        arriba_btn.setOnClickListener(this);
        abajo_btn.setOnClickListener(this);
        calzado_btn.setOnClickListener(this);
        vestido_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        hideRevealView();
        switch (v.getId()) {

            case R.id.arriba:

                break;
            case R.id.abajo:

                break;
            case R.id.calzado:

                break;
            case R.id.vestido:

                break;
        }
    }

    private void hideRevealView() {
        if (mRevealView.getVisibility() == View.VISIBLE) {
            mRevealView.setVisibility(View.GONE);
            hidden = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_probador, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_añadir:

                int cx = (mRevealView.getLeft() + mRevealView.getRight());
                int cy = mRevealView.getTop();
                int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

                //Below Android LOLIPOP Version
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    SupportAnimator animator =
                            ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(700);

                    SupportAnimator animator_reverse = animator.reverse();

                    if (hidden) {
                        mRevealView.setVisibility(View.VISIBLE);
                        animator.start();
                        hidden = false;
                    } else {
                        animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                            @Override
                            public void onAnimationStart() {

                            }

                            @Override
                            public void onAnimationEnd() {
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;

                            }

                            @Override
                            public void onAnimationCancel() {

                            }

                            @Override
                            public void onAnimationRepeat() {

                            }
                        });
                        animator_reverse.start();
                    }
                }
                // Android LOLIPOP And ABOVE Version
                else {
                    if (hidden) {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                        mRevealView.setVisibility(View.VISIBLE);
                        anim.start();
                        hidden = false;
                    } else {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, radius, 0);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;
                            }
                        });
                        anim.start();
                    }
                }
                return true;

            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
