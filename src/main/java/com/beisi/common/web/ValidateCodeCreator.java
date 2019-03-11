package com.beisi.common.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 验证码生成器
 * 
 */
public class ValidateCodeCreator {
	/**
	 * 图片
	 */
	private ByteArrayInputStream image;

	/**
	 * 验证码
	 */
	private String str;

	private ValidateCodeCreator() {
		init();
	}

	/*
	 * 取得ValidateCodeCreator实例
	 */
	public static ValidateCodeCreator Instance() {
		return new ValidateCodeCreator();
	}

	/*
	 * 取得验证码图片
	 */
	public ByteArrayInputStream getImage() {
		return this.image;
	}

	/*
	 * 取得图片的验证码
	 */
	public String getString() {
		return this.str;
	}

	private void init() {
		// 在内存中创建图象
		int width = 68, height = 26;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		// g.setColor(getRandColor(200, 250));
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Arial", Font.BOLD, 25));

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		String CodeChar = "1234567890abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(CodeChar.charAt(random.nextInt(CodeChar.length())));
			sRand += rand;
			g.setColor(new Color(random.nextInt(180), random.nextInt(180), random.nextInt(180)));
			g.drawString(rand, 14 * i + 4, 22);
		}
		g.setColor(new Color(195, 195, 195));
		g.drawRect(0, 0, width - 1, height - 1);
		// 赋值验证码
		this.str = sRand;

		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(30);
			int yl = random.nextInt(30);
			g.setColor(getRandColor(50, 200));
			g.drawLine(x, y, (x + xl) > width ? (width - 1) : (x + xl), (y + yl) > height ? (height - 1) : (y + yl));
		}

		// 图象生效
		g.dispose();
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(image, "JPEG", imageOut);
			imageOut.close();
			input = new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
			System.out.println("验证码图片产生出现错误：" + e.toString());
		}

		this.image = input;/* 赋值图像 */
	}

	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
