package window;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
	private final String title;
	private int width;
	private int height;
	private long windowHandle;
//	private boolean resized;
	private boolean vSync;
	public Window(String title, int width, int height, boolean vSync) {
		super();
		this.title = title;
		this.width = width;
		this.height = height;
//		this.resized = false;
		this.vSync = vSync;
	}
	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);	
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        
        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if(windowHandle==NULL) {
        	throw new RuntimeException("Failed to create GLFW Window");
        }
        glfwSetFramebufferSizeCallback(windowHandle, (window,width,height)->{
        	this.width=width;
        	this.height = height;
        });
        glfwSetKeyCallback(windowHandle, (window,key,scancode,action,mods)->{
        	if(key==GLFW_KEY_ESCAPE&&action==GLFW_RELEASE) {
        		glfwSetWindowShouldClose(windowHandle, true);
        	}
        });
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                windowHandle,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
        );
        glfwMakeContextCurrent(windowHandle);
        if(isvSync()) {
        	glfwSwapInterval(1);
        }
        glfwShowWindow(windowHandle);
        GL.createCapabilities();
        glClearColor(0.f, 0.f, 0.f, 0.f);
	}
	public void update(){
		glfwSwapBuffers(windowHandle);
		glfwPollEvents();
	}
	public void setClearColor(float r,float g,float b,float alpha) {
		glClearColor(r, g, b, alpha);
	}
	public boolean isKeyPressed(int keyCode) {
		return glfwGetKey(windowHandle, keyCode)==GLFW_PRESS;
	}
	public boolean windowShouldCole() {
		return glfwWindowShouldClose(windowHandle);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isvSync() {
		return vSync;
	}
	public void setvSync(boolean vSync) {
		this.vSync = vSync;
	}
}
