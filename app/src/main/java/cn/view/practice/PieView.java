package cn.view.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * 饼状图
 */
public class PieView extends View {

    private int mWidth;

    private int mHeight;

    // 默认颜色值
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private Paint mPaint = new Paint();

    private List<PieData> mPies;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPies == null || mPies.isEmpty()) {
            return;
        }

        canvas.translate(mWidth / 2, mHeight / 2);

        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);

        RectF rect = new RectF(-r, -r, r, r);

        float curStartAngle = 0;

        for (int i = 0; i < mPies.size(); i++) {
            PieData pieData = mPies.get(i);
            int color = pieData.getColor();
            mPaint.setColor(color);
            canvas.drawArc(rect, curStartAngle, pieData.getAngle(), true, mPaint);
            curStartAngle += pieData.getAngle();
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 数据
     */
    public static class PieData {

        /**
         * 用户设置
         */
        // 名称
        private String name;
        // 值大小
        private float value;

        // 百分比
        private float percentage;

        /**
         * 自动生成
         */
        // 饼颜色
        private int color;
        // 角度
        private float angle;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }

        public float getAngle() {
            return angle;
        }

        public void setAngle(float angle) {
            this.angle = angle;
        }
    }

    /**
     * 设置数据
     *
     * @param pies
     */
    public void setData(List<PieData> pies) {
        if (pies == null || pies.isEmpty()) {
            return;
        }
        this.mPies = pies;
        initData(pies);
        invalidate();
    }


    /**
     * 初始数据，将数值转换成View需要的数据
     *
     * @param pies
     */
    private void initData(List<PieData> pies) {

        // 总值
        float sumValue = 0;

        // 第一遍确定总数和颜色
        int size = pies.size();
        for (int i = 0; i < size; i++) {
            PieData pieData = pies.get(i);
            sumValue += pieData.getValue();

            // 颜色
            pieData.setColor(mColors[i % mColors.length]);
        }

        for (int i = 0; i < size; i++) {
            PieData pieData = pies.get(i);


            pieData.setPercentage(pieData.getValue() / sumValue);
            pieData.setAngle(pieData.getPercentage() * 360);
        }

    }

}
