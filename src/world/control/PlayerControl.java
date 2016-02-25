/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package world.control;

import com.jme3.app.Application;
import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import world.World;

/**
 * Controls player movement by using a CharacterControl which has to be added to
 * the player Spatial.
 *
 * @author Marco Klein
 */
public class PlayerControl extends WorldControl implements ActionListener {
    
    private BetterCharacterControl characterControl;
    private boolean left = false, right = false, up = false, down = false;
    private float speed = 3;

    /**
     * Used to decrease the creation of new objects while calculating with vectors.
     */
    private Vector3f tempVector = new Vector3f();
    private Vector3f tempVector2 = new Vector3f();
    private Vector3f tempVector3 = new Vector3f();
    
    private int maxJumpsInAir = 2;
    private int jumpsInAir = 0;
    
    private Camera cam;

    public PlayerControl() {
    }

    public PlayerControl(World world, float speed) {
        super(world);
        this.speed = speed;
    }
    
    
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            // initialize
            // get character control
            characterControl = spatial.getControl(BetterCharacterControl.class);
            if (characterControl == null) {
                throw new UnsupportedOperationException("Player needs a BetterCharacterControl.class.");
            }
            Application app = world.getApp();
            
            // set up key bindings
            InputManager inputManager = app.getInputManager();
            inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
            inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
            inputManager.addMapping("CharForward", new KeyTrigger(KeyInput.KEY_W));
            inputManager.addMapping("CharBackward", new KeyTrigger(KeyInput.KEY_S));
            inputManager.addMapping("CharJump", new KeyTrigger(KeyInput.KEY_SPACE));
            inputManager.addListener(this, "CharLeft", "CharRight");
            inputManager.addListener(this, "CharForward", "CharBackward");
            inputManager.addListener(this, "CharJump");
            
            // set up camera
            cam = app.getCamera();

            
        }
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        // calculate walking direction and move character
        tempVector.zero();
        
        
        // cam left
        tempVector2.set(cam.getLeft());
        tempVector2.y = 0;
        tempVector2.normalizeLocal();
        // cam direction
        tempVector3.set(cam.getDirection());
        tempVector3.y = 0;
        tempVector3.normalizeLocal();
        
        if (left) {
            tempVector.addLocal(tempVector2);
        }
        if (right) {
            tempVector.addLocal(tempVector2.negateLocal());
        }
        if (up) {
            tempVector.addLocal(tempVector3);
        }
        if (down) {
            tempVector.addLocal(tempVector3.negateLocal());
        }
        
        if (!characterControl.isOnGround()) {
            // TODO set airtime see http://wiki.jmonkeyengine.org/doku.php/jme3:advanced:walking_character
        } else {
            
        }
        
        tempVector.normalizeLocal().multLocal(speed);
        characterControl.getWalkDirection().set(tempVector);
        
        // animate player movement by rotating the player (since its geometry is a sphere)
        // TODO animate the player somewhere else (maybe an extra PlayerAnimationControl) only for better code design
        // get player geometry (could be implemented nicer - see todo on top)
        Spatial playerGeom = ((Node) spatial).getChild("Model");
        playerGeom.rotate(tpf * tempVector.x, 0, tpf * tempVector.z);
        
        // reset jump if on ground or not moved (f.i. sticked to wall)
//        if (characterControl.getVelocity().lengthSquared() < 0.01f) {
//            // not moved
//            jumpsInAir = 0;
//        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        switch (name) {
            case "CharLeft":
                if (isPressed) {
                    left = true;
                } else {
                    left = false;
                }
                break;
            case "CharRight":
                if (isPressed) {
                    right = true;
                } else {
                    right = false;
                }
                break;
            case "CharForward":
                if (isPressed) {
                    up = true;
                } else {
                    up = false;
                }
                break;
            case "CharBackward":
                if (isPressed) {
                    down = true;
                } else {
                    down = false;
                }
                break;
            case "CharJump":
                if (isPressed) {
                    if (characterControl.isOnGround()) {
                        jumpsInAir = 0;
                    }
                    if (jumpsInAir < maxJumpsInAir) {
                        // perform jump action only on press
                        jumpsInAir++;
                        characterControl.getVelocity().addLocal(characterControl.getJumpForce());
                    }
                }
                break;
        }
    }
    
    
}
